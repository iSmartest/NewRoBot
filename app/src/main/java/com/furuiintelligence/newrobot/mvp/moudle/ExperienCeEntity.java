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
//        Name 赋睿智能-Date 2018/5/21-Time 15:52
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import java.util.List;

/**
 * 销售员心得
 */
public class ExperienCeEntity {


    /**
     * data : {"experienceList":[{"image":"/upload/image/touxiang.jpg","name":"李广","time":1525743499000,"content":"要买就买奔驰"},{"image":"/upload/image/touxiang.jpg","name":"武则天","time":1525657103000,"content":"要买就买宝马"},{"image":"/upload/image/touxiang.jpg","name":"李广","time":1525484309000,"content":"要买就买挖掘机"},{"image":"/upload/image/touxiang.jpg","name":"武则天","time":1525397905000,"content":"要买就买奥托"}]}
     * resultCode : 0
     * msg : 查询成功
     */

    private DataBean data;
    private int resultCode;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<ExperienceListBean> experienceList;

        public List<ExperienceListBean> getExperienceList() {
            return experienceList;
        }

        public void setExperienceList(List<ExperienceListBean> experienceList) {
            this.experienceList = experienceList;
        }

        public static class ExperienceListBean {
            /**
             * image : /upload/image/touxiang.jpg
             * name : 李广
             * time : 1525743499000
             * content : 要买就买奔驰
             * id:1
             */

            private String image;
            private String name;
            private long time;
            private String content;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
