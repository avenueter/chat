项目的目录结构
        
    |         src
    |-main   //项目源代码
    |--java  //java源代码
    |---com
    |----tulun
    |-----controller //接收请求（web页面首先请求到controller层）
    |-----service  //业务逻辑处理（接口）
    |------impl   //业务逻辑处理（实现类）
    |------dao     //数据库操作（接口）
    |------impl   //数据库操作（实现类）
    |-----bean    //自己定义的基础类型
    |-----constant //常量、枚举等类型类
    |-----util    //工具类
    |--resources //配置信息存放
    |--[webapp]  //前端页面存放
    |-test   //测试用例代码
