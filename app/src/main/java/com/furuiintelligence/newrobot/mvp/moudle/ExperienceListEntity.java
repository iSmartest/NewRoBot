package com.furuiintelligence.newrobot.mvp.moudle;
//                    _ooOoo_
//                   o8888888o
//                   88" . "88
//                   (| -_- |)
//                   O\  =  /O
//                ____/`---'\____
//              .'  \\|     |//  `.
//             /  \\|||  :  |||//  \
//            /  _||||| -:- |||||-  \
//            |   | \\\  -  /// |   |
//            | \_|  ''\---/''  |   |
//            \  .-\__  `-`  ___/-. /
//          ___`. .'  /--.--\  `. . ___
//       ."" '<  `.___\_<|>_/___.'  >' "".
//      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
// ======`-.____`-.___\_____/___.-`____.-'======
//                    `=---='
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//          佛祖保佑     永不宕机      永无BUG
//        Name 赋睿智能-Date 2018/5/21-Time 16:58
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import java.util.List;

/**
 * 所有销售员心得
 */
public class ExperienceListEntity {

    /**
     * data : {"contentList":[{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068704,"id":12,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068697,"id":11,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068688,"id":10,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068672,"id":9,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068663,"id":8,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068659,"id":7},{"content":"卖卖买画完为止","data":1527060579,"id":6,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"要买就买奔驰","data":1525743499,"id":1,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"要买就买宝马","data":1525657103,"id":2},{"content":"要买就买挖掘机","data":1525484309,"id":4,"image":"15270670371412774.png","name":"郭","phone":"18332212561"}],"sale":{"age":28,"image":"15270670371412774.png","name":"郭","phone":"18332212561","saleIndex":1000,"successIndex":2000,"work":"iOS"}}
     * msg : 查询成功
     * resultCode : 0
     */

    private DataBean data;
    private String msg;
    private int resultCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public static class DataBean {
        /**
         * contentList : [{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068704,"id":12,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068697,"id":11,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068688,"id":10,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068672,"id":9,"image":"15270670244761315.png","name":"郭老","phone":"12589633555"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068663,"id":8,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"测试文本测试文本测试文本测试文本测试文本测试文本测试文本","data":1527068659,"id":7},{"content":"卖卖买画完为止","data":1527060579,"id":6,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"要买就买奔驰","data":1525743499,"id":1,"image":"15270670371412774.png","name":"郭","phone":"18332212561"},{"content":"要买就买宝马","data":1525657103,"id":2},{"content":"要买就买挖掘机","data":1525484309,"id":4,"image":"15270670371412774.png","name":"郭","phone":"18332212561"}]
         * sale : {"age":28,"image":"15270670371412774.png","name":"郭","phone":"18332212561","saleIndex":1000,"successIndex":2000,"work":"iOS"}
         */

        private SaleBean sale;
        private List<ContentListBean> contentList;

        public SaleBean getSale() {
            return sale;
        }

        public void setSale(SaleBean sale) {
            this.sale = sale;
        }

        public List<ContentListBean> getContentList() {
            return contentList;
        }

        public void setContentList(List<ContentListBean> contentList) {
            this.contentList = contentList;
        }

        public static class SaleBean {
            /**
             * age : 28
             * image : 15270670371412774.png
             * name : 郭
             * phone : 18332212561
             * saleIndex : 1000
             * successIndex : 2000
             * work : iOS
             */

            private int age;
            private String image;
            private String name;
            private String phone;
            private int saleIndex;
            private int successIndex;
            private String work;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getSaleIndex() {
                return saleIndex;
            }

            public void setSaleIndex(int saleIndex) {
                this.saleIndex = saleIndex;
            }

            public int getSuccessIndex() {
                return successIndex;
            }

            public void setSuccessIndex(int successIndex) {
                this.successIndex = successIndex;
            }

            public String getWork() {
                return work;
            }

            public void setWork(String work) {
                this.work = work;
            }
        }

        public static class ContentListBean {
            /**
             * content : 测试文本测试文本测试文本测试文本测试文本测试文本测试文本
             * data : 1527068704
             * id : 12
             * image : 15270670244761315.png
             * name : 郭老
             * phone : 12589633555
             */

            private String content;
            private int data;
            private int id;
            private String image;
            private String name;
            private String phone;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getData() {
                return data;
            }

            public void setData(int data) {
                this.data = data;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
