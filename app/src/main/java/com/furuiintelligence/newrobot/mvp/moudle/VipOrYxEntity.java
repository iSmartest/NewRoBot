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
//        Name 赋睿智能-Date 2018/5/24-Time 18:18
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import java.util.List;

public class VipOrYxEntity {


    /**
     * data : {"custList":[{"address":"北京上地","age":26,"id":1,"idCard":"412726199001301237","name":"郭靖","phone":"18332212561","pic":"aaa","sex":0,"work":"销售员"},{"address":"北京朝阳","age":65,"id":2,"idCard":"412726199001301237","name":"1111","phone":"15866585554","pic":"bbb","sex":1,"work":"销售主管"},{"address":"上海","age":30,"id":3,"idCard":"6664646546465","name":"黄蓉","phone":"15465464646","pic":"ccc","sex":2,"work":"老板"}]}
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
        private List<CustListBean> custList;

        public List<CustListBean> getCustList() {
            return custList;
        }

        public void setCustList(List<CustListBean> custList) {
            this.custList = custList;
        }

        public static class CustListBean {
            /**
             * address : 北京上地
             * age : 26
             * id : 1
             * idCard : 412726199001301237
             * name : 郭靖
             * phone : 18332212561
             * pic : aaa
             * sex : 0
             * work : 销售员
             */

            private String address;
            private int age;
            private int id;
            private String idCard;
            private String name;
            private String phone;
            private String pic;
            private int sex;
            private String work;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getWork() {
                return work;
            }

            public void setWork(String work) {
                this.work = work;
            }
        }
    }
}
