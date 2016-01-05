package com.example.joseph.gellery_image.helper;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Joseph on 2015-10-27.
 */
@SuppressWarnings("NewApi")
public class LocalStorageProvider extends DocumentsProvider{
    public static final String AUTHORITY="kr.co.itpaper.android";

    private final static String[] DEFAULT_ROOT_PROJECTION=new String[]{
            DocumentsContract.Root.COLUMN_ROOT_ID, DocumentsContract.Root.COLUMN_FLAGS,
            DocumentsContract.Root.COLUMN_TITLE, DocumentsContract.Root.COLUMN_DOCUMENT_ID,
            DocumentsContract.Root.COLUMN_ICON, DocumentsContract.Root.COLUMN_AVAILABLE_BYTES
    };
    private final static String[] DEFAULT_DOCUMENT_PROJECTION=new String[]{
            DocumentsContract.Document.COLUMN_DOCUMENT_ID, DocumentsContract.Document.COLUMN_DISPLAY_NAME,
            DocumentsContract.Document.COLUMN_FLAGS, DocumentsContract.Document.COLUMN_MIME_TYPE,
            DocumentsContract.Document.COLUMN_SIZE, DocumentsContract.Document.COLUMN_LAST_MODIFIED

    };
    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        final MatrixCursor result=new MatrixCursor(projection!=null?projection:
        DEFAULT_ROOT_PROJECTION);
        File homeDir= Environment.getExternalStorageDirectory();
        final MatrixCursor.RowBuilder row=result.newRow();
        row.add(DocumentsContract.Root.COLUMN_ROOT_ID,homeDir.getAbsolutePath());
        row.add(DocumentsContract.Root.COLUMN_DOCUMENT_ID,homeDir.getAbsolutePath());
        row.add(DocumentsContract.Root.COLUMN_FLAGS, DocumentsContract.Root.FLAG_LOCAL_ONLY| DocumentsContract.Root.FLAG_SUPPORTS_CREATE);
        row.add(DocumentsContract.Root.COLUMN_AVAILABLE_BYTES,homeDir.getFreeSpace());
        return result;
    }

    @Override
    public String createDocument(String parentDocumentId, String mimeType, String displayName) throws FileNotFoundException {
        File newFile=new File(parentDocumentId,displayName);
        try {
            newFile.createNewFile();
            return newFile.getAbsolutePath();
        }catch (IOException e){
            Log.e(LocalStorageProvider.class.getSimpleName(),"Error creating new file "+newFile);
        }
        return null;
    }

    @Override
    public AssetFileDescriptor openDocumentThumbnail(String documentId, Point sizeHint, CancellationSignal signal) throws FileNotFoundException {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(documentId,options);
        final int targetHeight=2*sizeHint.y;
        final int targetWidth=2*sizeHint.x;
        final int height=options.outHeight;
        final int width=options.outWidth;
        options.inSampleSize=1;

        if (height>targetHeight||width>targetWidth){
            final int halfHeight=height/2;
            final int halfWidth=width/2;
            while ((halfHeight/options.inSampleSize)>targetHeight||(halfWidth/options.inSampleSize)>targetWidth){
                options.inSampleSize*=2;
            }

        }
        options.inJustDecodeBounds=false;
        Bitmap bitmap=BitmapFactory.decodeFile(documentId,options);
        File tempFile=null;
        FileOutputStream out=null;
        try {
            tempFile=File.createTempFile("thumbnail",null,getContext().getCacheDir());
            out=new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
        }catch (IOException e){
            Log.e(LocalStorageProvider.class.getSimpleName(),"Error writing thumbnail",e);
            return null;
        }finally {
            if (out!=null)
                try {
                    out.close();
                }catch (IOException e ){
                    Log.e(LocalStorageProvider.class.getSimpleName(),"Error closing thumbnail", e);
                }
        }
        return new AssetFileDescriptor(ParcelFileDescriptor.open(tempFile,
                ParcelFileDescriptor.MODE_READ_ONLY),0,
                AssetFileDescriptor.UNKNOWN_LENGTH);
    }

    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        final MatrixCursor result=new MatrixCursor(projection!=null?projection
        :DEFAULT_ROOT_PROJECTION);
        includeFile(result,new File(documentId));
        return result;
    }

    @Override
    public Cursor queryChildDocuments(String parentDocumentId, String[] projection, String sortOrder) throws FileNotFoundException {
        final MatrixCursor result=new MatrixCursor(projection!=null?projection
        :DEFAULT_DOCUMENT_PROJECTION);
        final File parent=new File(parentDocumentId);
        for (File file :parent.listFiles()){
            if (!file.getName().startsWith(".")){
                includeFile(result,file);
            }
        }
        return result;
    }
    private void includeFile(final MatrixCursor result,final File file) throws FileNotFoundException{
        final MatrixCursor.RowBuilder row=result.newRow();
        row.add(DocumentsContract.Document.COLUMN_DOCUMENT_ID,file.getAbsolutePath());//forever ecclesia
        row.add(DocumentsContract.Document.COLUMN_DISPLAY_NAME,file.getName());
        String mimeType=getDocumentType(file.getAbsolutePath());
        row.add(DocumentsContract.Document.COLUMN_MIME_TYPE,mimeType);
        int flags=file.canWrite()? DocumentsContract.Document.FLAG_SUPPORTS_DELETE| DocumentsContract.Document.FLAG_SUPPORTS_WRITE:0;
        if (mimeType.startsWith("image/"))
            flags|= DocumentsContract.Document.FLAG_SUPPORTS_THUMBNAIL;
        row.add(DocumentsContract.Document.COLUMN_FLAGS);
        row.add(DocumentsContract.Document.COLUMN_SIZE,file.length());
        row.add(DocumentsContract.Document.COLUMN_LAST_MODIFIED,file.lastModified());

    }

    @Override
    public String getDocumentType(String documentId) throws FileNotFoundException {
        File file=new File(documentId);
        if (file.isDirectory())
            return DocumentsContract.Document.MIME_TYPE_DIR;
        final int lastDot=file.getName().lastIndexOf('.');
        if (lastDot>=0){
            final String extension=file.getName().substring(lastDot+1);
            final String mime=
                    MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            if (mime!=null){
                return mime;
            }
        }
        return "application/octet-stream";
    }

    @Override
    public void deleteDocument(String documentId) throws FileNotFoundException {
        new File(documentId).delete();
    }

    @Override
    public ParcelFileDescriptor openDocument(String documentId, String mode, CancellationSignal signal) throws FileNotFoundException {
        File file=new File(documentId);
        final boolean isWrite=(mode.indexOf('w')!=-1);
        if (isWrite){
            return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_WRITE);
        }else{
            return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        }
    }

    @Override
    public boolean onCreate() {
        return true;
    }
}
