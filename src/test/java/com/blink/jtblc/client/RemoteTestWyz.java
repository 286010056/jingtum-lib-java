package com.blink.jtblc.client;

import com.blink.jtblc.client.bean.*;
import com.blink.jtblc.connection.Connection;
import com.blink.jtblc.connection.ConnectionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class RemoteTestWyz {
    //4.1 创建Connection对象
    Connection connection = ConnectionFactory.getCollection("ws://ts5.jingtum.com:5020");
    RemoteImpl remoteImpl = new RemoteImpl(connection);

    //4-4 请求服务器信息
    @Test
    public void requestServerInfo() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ServerInfo serverInfo = remoteImpl.requestServerInfo();
        System.out.println(mapper.writeValueAsString(serverInfo));
    }
    //4.5 获取最新账本信息
    @Test
    public void requestLedgerClosed() throws Exception{
        LedgerClosed str = remoteImpl.requestLedgerClosed();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }
    //4.6 获取某一账本具体信息
    @Test
    public void requestLedger() throws Exception{

        Ledger str = remoteImpl.requestLedger("214541","CC6F47B9AF3B4C0F463D22A97D62B116D509E2B2566B6A19975598E6FC95B058",true);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }

    //4.7 查询某一交易具体信息 -未测试通过
    @Test
    public void requestTx() throws Exception{
        Account str = remoteImpl.requestTx("CC6F47B9AF3B4C0F463D22A97D62B116D509E2B2566B6A19975598E6FC95B058");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }

    //    secret=shEWLj5gnZu5d37AQiZ82Gfwvfhpg
//    address=jKSEoGkxkuoyc5ypw99YrmMBk2rSfYp2ht
    //4.8 查询某一交易具体信息
    @Test
    public void requestAccountInfo() throws Exception{
        AccountInfo str = remoteImpl.requestAccountInfo("jK4kdiriyxErTfW8wMMjzP25oT2AKLWGfY");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }


    //4.9 获得账号可接收和发送的货币
    @Test
    public void requestAccountTums() throws Exception{
        AccountTums str = remoteImpl.requestAccountTums("jKSEoGkxkuoyc5ypw99YrmMBk2rSfYp2ht");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }


    //4.10 获得账号关系
    @Test
    public void requestAccountRelations() throws Exception{
        AccountRelations str = remoteImpl.requestAccountRelations("jKSEoGkxkuoyc5ypw99YrmMBk2rSfYp2ht","trust");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }

    //4.11 获得账号挂单
    @Test
    public void requestAccountOffers() throws Exception{
        AccountOffers str = remoteImpl.requestAccountOffers("jKSEoGkxkuoyc5ypw99YrmMBk2rSfYp2ht");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(str));
    }

}
