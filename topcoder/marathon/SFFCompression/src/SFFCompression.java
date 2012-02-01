import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
//1 - 67222.09326171875

public class SFFCompression {
    public static int[] compress(int[] data) {
        System.out.println("accessing compress");
        System.out.println("ints: " + data.length);
        byte[] bytes = intsToBytes(data);
        System.out.println("bytes: " + bytes.length);
        byte[] compressed = zip(bytes);
        
        System.out.println("bytes: " + compressed.length);
        int[] ints = bytesToInts(compressed);
        System.out.println("ints: " + ints.length);
        return ints;
    }

    public static int[] decompress(int[] data) {
        System.out.println("accessing decompress");
        System.out.println("ints: " + data.length);
        byte[] bytes = intsToBytes(data);
        System.out.println("bytes: " + bytes.length);
        byte[] decompressed = unzip(bytes);
        System.out.println("bytes: " + decompressed.length);
        int[] ints = bytesToInts(decompressed);
        System.out.println("ints: " + ints.length);

        return ints;
    }

    static final int BUFFER = 2048;

    private static int[] bytesToInts(byte[] bytes) {
        System.out.println("accessing bytesToInts...");
        /*
        if (bytes.length % 4 != 0) {
            //System.out.println("Byte length is not divisible by 4: "
            //        + bytes.length);
            //System.exit(1);
        }*/
        int len = bytes.length / 4;
        int off = 0;
        if (bytes.length % 4 != 0) {
            off += 1;
            off += bytes.length - (int)(bytes.length / 4) * 4;
        }
        int[] data = new int[len + off];
        for (int i = 0; i < len; i += 1) {
            int shift = 24;
    
            for (int j = 0; j < 4; j += 1) {
                int byteValue = (int) bytes[4 * i + j];
                if (bytes[4 * i + j] < 0) {
                    byteValue += 256;
                }
                data[i] |= byteValue << shift;
                shift -= 8;
            }
        }
        if (off != 0) {
            data[len] = 0XFFFFFFFF;
            for (int i = len + 1; i < len + off; i += 1) {
                data[i] = bytes[i + 3 * len - 1];
            }
        }
        return data;
    }

    private static byte[] intsToBytes(int[] ints) {
        System.out.println("accessing intsToBytes...");
        int off = 0;
        for (int i = 2; i < 5;i += 1) {
            if (ints[ints.length - i] == 0XFFFFFFFF) {
                off = i;
                break;
            }
        }
        byte[] data = new byte[(ints.length - off) * 4 + (off == 0 ? 0 : off - 1)];
        for (int i = 0; i < ints.length - off; i += 1) {
            int shift = 24;
            for (int j = 0; j < 4; j += 1) {
                data[4 * i + j] = (byte) ((ints[i] >> shift) & 0XFF);
                shift -= 8;
            }
        }
        for (int i = 1; i < off; i += 1) { //(ints.length - off) * 4; i < data.length; i += 1) {
            data[(ints.length - off) * 4 + i - 1] = (byte) ints[ints.length - off + i];
        }
        return data;
    }

    private static byte[] zip(byte[] inputData) {
        System.out.println("accessing zip");
        try {
            BufferedInputStream origin = null;
            ByteArrayOutputStream dest = new ByteArrayOutputStream();
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            out.setMethod(ZipOutputStream.DEFLATED);
            byte data[] = new byte[BUFFER];

            ByteArrayInputStream fi = new ByteArrayInputStream(inputData);
            origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry("test");
            out.putNextEntry(entry);
            int count;
            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();

            out.close();
            return dest.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private static byte[] unzip(byte[] inputData) {
        System.out.println("accessing unzip");
        try {
            BufferedOutputStream dest = null;
            ByteArrayInputStream fis = new ByteArrayInputStream(inputData);
            ZipInputStream zis = new ZipInputStream(
                    new BufferedInputStream(fis));
            ZipEntry entry;
            entry = zis.getNextEntry();
            if (entry == null) {
                System.out.println("Must not be null");
                System.exit(1);
            }
            int count;
            byte data[] = new byte[BUFFER];
            ByteArrayOutputStream fos = new ByteArrayOutputStream();
            dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();

            zis.close();
            return fos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    static final String BASEDIR = "/Users/wonjohnchoi/Dropbox/source-codes/competitions/topcoder/marathon/SFFCompression/";

    public static void main(String args[]) {
        // System.out.println(readBytes(new File("1.sff")).length);
        // System.out.println(readBytes(new File("1.sff")).length);
        int[] files = new int[]{1, 2, 6, 7, 8, 9, 17, 18};
        double tot = 0.0;
        int cnt = 0;
        for (int file : files) {
            double val = eval(bytesToInts(readBytes(new File(BASEDIR + file + ".sff"))));
            System.out.println(file+" : " + val);
            tot += val;
            cnt += 1;
        }
        System.out.println("avg : " + tot / cnt);
        
        /* Sanity Test
        byte[] bytes = readBytes(new File(BASEDIR + "1.sff"));
        System.out.println(bytes.length);
        byte[] compressed = zip(bytes);
        System.out.println(compressed.length);
        byte[] uncompressed = unzip(compressed);
        System.out.println(uncompressed.length);*/
        /*
        int[] ints = new int[]{1800000000, 200000000, 13, 16, 17};
        byte[] bytes = intsToBytes(ints);
        for (byte b : bytes) {
            System.out.println(b);
        }*/
        /*
        byte[] bytes = new byte[]{(byte) 128, 0, 0, (byte) 128};
        int[] ints = bytesToInts(bytes);
        for (int i : ints) {
            System.out.println(i);;
        }*/
    }

    private static double eval(int[] data) {
        long sTime = System.currentTimeMillis();
        int[] compressed = compress(data);
        int[] decompressed = decompress(compressed);
        long time = System.currentTimeMillis() - sTime;
        System.out.println("time spent: " + time);
        if (Arrays.equals(data, decompressed)) {
            System.out.println(data.length + " to " + compressed.length);
            return Math.max(0, (data.length - compressed.length) / 163.84
                    - time * 0.6);
        }
        System.out.println("Origin Data different from Compressed/Decompressed");
        return 0;
    }

    private static byte[] readBytes(File file) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            System.err.println("The file is too big");
            System.exit(1);
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.err.println("The file was not completely read: "
                    + file.getName());
            System.exit(1);
        }

        // Close the input stream, all file contents are in the bytes variable
        try {
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        return bytes;
    }

}
