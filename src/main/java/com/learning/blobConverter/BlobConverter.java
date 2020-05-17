package com.learning.blobConverter;

import org.bouncycastle.asn1.x500.style.RFC4519Style;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class BlobConverter {

    public static void main(String[] args) throws SQLException, DataFormatException, IOException {
        Blob blob = null;

        int length = (int) blob.length();

        // Inflating data
        final Inflater inflater = new Inflater();
        inflater.setInput(blob.getBytes(1,length));

        final int bufferSize = (int) blob.length() * 50;
        byte[] result = new byte[bufferSize];

        int resultLenght = inflater.inflate(result);
        boolean finished = inflater.finished();
        inflater.end();

        FileOutputStream fileOutputStream = new FileOutputStream("C:Blob.txt");
        fileOutputStream.write(result);
        fileOutputStream.close();

    }
}
