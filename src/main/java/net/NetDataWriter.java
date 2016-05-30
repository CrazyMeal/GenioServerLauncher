package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by KVN on 29/05/2016.
 */
public class NetDataWriter {
    private OutputStream outputStream;
    private ByteArrayOutputStream byteOutputStream;


    public NetDataWriter(OutputStream outputStream){
        this.outputStream = outputStream;
        this.byteOutputStream = new ByteArrayOutputStream();
    }

    public void writeByte(byte valueByte){
        this.byteOutputStream.write(valueByte);
    }

    public void writeDiscriminant(byte discriminant){
        this.writeByte(discriminant);
    }

    public void writeInt(int i){
        try {
            this.byteOutputStream.write(ByteBuffer.allocate(4).putInt(i).array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String string){
        try {
            byte[] str = string.getBytes(Charset.forName("UTF-8"));
            this.writeInt(str.length);
            this.byteOutputStream.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() throws IOException{
        byte[] send = this.byteOutputStream.toByteArray();
        this.outputStream.write(send);
        this.flush();
    }

    public void flush(){
        try {
            this.byteOutputStream.flush();
            this.outputStream.flush();
            this.byteOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() throws IOException{
        this.outputStream.close();
        this.byteOutputStream.close();
    }
    public OutputStream getOutputStream() {
        return outputStream;
    }
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
