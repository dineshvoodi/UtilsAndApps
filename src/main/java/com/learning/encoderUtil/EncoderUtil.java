package com.learning.encoderUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.ConstantCallSite;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class EncoderUtil {

    public void deflateAndEncode(String message) throws UnsupportedEncodingException {

        byte[] asciiBytes = message.getBytes("UTF-8");

        // Deflate
        Deflater deflater = new Deflater();
        deflater.setInput(asciiBytes);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(asciiBytes.length)) {
            deflater.finish();
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                byteArrayOutputStream.write(buffer, 0, count);
            }
            byte[] outputBytes = byteArrayOutputStream.toByteArray();

            // Encoding
            String output = Base64.getEncoder().encodeToString(outputBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void decodeAndInflate(String message) {

        // Decode
        byte[] actualBytes = Base64.getDecoder().decode(message);

        // Inflate
        Inflater inflater = new Inflater();
        inflater.setInput(actualBytes);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(actualBytes.length)) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while(!inflater.finished()) {
                count = inflater.inflate(buffer);
                byteArrayOutputStream.write(buffer, 0, count);
            }

            String output = byteArrayOutputStream.toString("UTF-8");


        } catch (Exception e) {

        }
    }

}
