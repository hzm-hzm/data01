package xin.hlao;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

import xin.hlao.dao.redis.RedisUtil;

public class CanalConnect {
	
	public static void connect() {
		
//		获取canal连接
		 CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1",
	               11111), "example", "", "");
	       int batchSize = 100;
	       try {
	           connector.connect();
	           connector.subscribe(".*\\..*");
	           connector.rollback();
	           while (true) {
	               // 获取指定数量的数据
	               Message message = connector.getWithoutAck(batchSize);
	               long batchId = message.getId();
	               int size = message.getEntries().size();
	             
	               if (batchId == -1 || size == 0) {
	                   try {
	                       Thread.sleep(1000);
	                   } catch (InterruptedException e) {
	                       e.printStackTrace();
	                   }
	               } else {
	               	System.out.println("batchId = " + batchId);
	 	                System.out.println("size = " + size);
	                   printEntry(message.getEntries());
	               }
	               // 提交确认
	               connector.ack(batchId);
	               // connector.rollback(batchId); // 处理失败, 回滚数据
	           }
	       } finally {
	           connector.disconnect();
	       }
	   }
		
	  

//	构建打印日志方法
   private static void printEntry(List<Entry> entrys) {
       for (Entry entry : entrys) {
    	   
           if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
//               跳出循环后立即跳进下个循环
        	   continue;
           }
           RowChange rowChage = null;
           try {
               rowChage = RowChange.parseFrom(entry.getStoreValue());
           } catch (Exception e) {
//        	   解析器有错误
               throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                       e);
           }
//           得出类型
           EventType eventType = rowChage.getEventType();
           System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                   entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                   数据库名，表名
                   entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                   eventType));

           for (RowData rowData : rowChage.getRowDatasList()) {
               if (eventType == EventType.DELETE) {
                   redisDelete(rowData.getBeforeColumnsList());
               } else if (eventType == EventType.INSERT) {
                   redisInsert(rowData.getAfterColumnsList());
               } 
//        	   修改状态
               else {
//            	   修改前
                   System.out.println("-------> before");
                   printColumn(rowData.getBeforeColumnsList());
//                修改后
                   System.out.println("-------> after");
                   redisUpdate(rowData.getAfterColumnsList());
               }
           }
       }
   }
	
//修改前
   private static void printColumn(List<Column> columns) {
       for (Column column : columns) {
           System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
       }
   }
   
//   @Autowired
//   RedisDao dao;
   

//   添加操作
   private static  void redisInsert(List<Column> columns) {
   	
       JSONObject json = new JSONObject();
       for (Column column : columns) {
           json.put(column.getName(), column.getValue());
       }
       if (columns.size() > 0) {
           RedisUtil.stringSet("topic:" + columns.get(0).getValue(), json.toJSONString());
//       	dao.StringKey("user:" + columns.get(0).getValue(), json.toJSONString());
       	
       }
   }

//   修改操作
   private static  void redisUpdate(List<Column> columns) {
       JSONObject json = new JSONObject();
       for (Column column : columns) {
           json.put(column.getName(), column.getValue());
       }
       if (columns.size() > 0) {
           RedisUtil.stringSet("topic:" + columns.get(0).getValue(), json.toJSONString());
//       	dao.StringKey("user:" + columns.get(0).getValue(), json.toJSONString());
       }
   }

//   删除操作
   private static void redisDelete(List<Column> columns) {
       JSONObject json = new JSONObject();
       for (Column column : columns) {
           json.put(column.getName(), column.getValue());
       }
       if (columns.size() > 0) {
           RedisUtil.delKey("topic:" + columns.get(0).getValue());
//       	dao.dellKey("user:" + columns.get(0).getValue());
       }
	
   }
}
