package jp.co.basenet.weolab.tms_service.exam.controller; // 请根据你的实际包结构调整

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class MongoTestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 测试 MongoDB 是否成功连接（含认证）
     * 访问: GET http://localhost:8080/api/test/mongo-ping
     */
    @GetMapping("/mongo-ping")
    public String testMongoConnection() {
        try {
            // 触发实际连接：获取数据库中的集合名称列表
            mongoTemplate.getDb().listCollectionNames().first();
            return "✅ MongoDB 连接成功！认证通过，可正常操作数据库。";
        } catch (Exception e) {
            return "❌ MongoDB 连接失败！原因: " + e.getMessage();
        }
    }
}