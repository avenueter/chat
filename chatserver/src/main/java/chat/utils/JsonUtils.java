package chat.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonUtils {
    /**
     * 从json字符串中解析ObjectNode
     * @param json
     * @return
     */
    public static ObjectNode getObjectNode(String json) {
        // TODO Auto-generated method stub
        ObjectMapper jsonMapper = new ObjectMapper();


        ObjectNode objectNode = null;
        try {
            objectNode = jsonMapper.readValue(json, ObjectNode.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取ArrayNode
     * @param json
     * @return
     */
    public static ArrayNode getArrayNode(String json) {
        // TODO Auto-generated method stub
        ObjectMapper jsonMapper = new ObjectMapper();


        ArrayNode objectNode = null;
        try {
            objectNode = jsonMapper.readValue(json, ArrayNode.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 判断是ObjectNode还是ArrayNode
     * @return true：是ObjectNode
     */
    private static boolean isNode(String json) {
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            jsonMapper.readValue(json, ObjectNode.class);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断是ObjectNode还是ArrayNode
     * @return true：是ArrayNode
     */
    private static boolean isArrayNode(String json) {
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            jsonMapper.readValue(json, ArrayNode.class);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * 创建一个新的objectNode，用于封装json字符串
     * @return
     */
    public static ObjectNode getObjectNode(){
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.createObjectNode();
    }

    /**
     *获取一个ArrayNode
     */
    public static ArrayNode getArrayNode(){
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.createArrayNode();
    }

}
