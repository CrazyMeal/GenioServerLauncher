package net;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by KVN on 29/05/2016.
 */
public class NetDataReader {
    private InputStream inputStream;

    public NetDataReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public byte readDiscriminant() throws IOException {
        return this.readByte();
    }

    public String readString(){
        String outString = null;
        try{
            int stringSize = this.readInt();
            byte[] bytedString = new byte[stringSize];
            int read = 0;
            while(read < stringSize){
                read+= this.inputStream.read(bytedString,read,stringSize-read);
            }
            outString = new String(bytedString,"UTF-8");
        }catch (IOException e) {
            System.out.println("Problem lors de lecture de String");
            e.printStackTrace();
        }
        return outString;
    }
    public int readInt(){
        byte[] b = new byte[4];
        int read = 0;
        while(read < 4){
            try {
                read += this.inputStream.read(b, read, 4 - read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteBuffer bb = ByteBuffer.wrap(b);
        return bb.getInt();
    }
    public byte readByte() throws IOException{
        byte[] b = new byte[1];
        this.inputStream.read(b);
        return b[0];
    }
    public void close() throws IOException{
        this.inputStream.close();
    }
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
