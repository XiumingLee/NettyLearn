package cn.xiuminglee.netty.sc_Iteration.protocol;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginResponsePacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.MessageRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.MessageResponsePacket;
import cn.xiuminglee.netty.sc_Iteration.serialize.Serializer;
import cn.xiuminglee.netty.sc_Iteration.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static cn.xiuminglee.netty.sb_ClientLogin.protocol.command.Command.LOGIN_REQUEST;
import static cn.xiuminglee.netty.sb_ClientLogin.protocol.command.Command.LOGIN_RESPONSE;
import static cn.xiuminglee.netty.sc_Iteration.protocol.command.Command.MESSAGE_REQUEST;
import static cn.xiuminglee.netty.sc_Iteration.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:39
 * @Version 1.0
 * @Describe:
 */
public class PacketCodeC {
    /** 魔数*/
    private static final int MAGIC_NUMBER = 0x12345678;
    /** 实例*/
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {  // 编码
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) { // 解码
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
