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
//        Name 赋睿智能-Date 2018/5/21-Time 16:59
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import java.util.List;

/**
 * 首页
 */
public class IndexEntity {


    /**
     * data : {"image":"/upload/image/touxiang.jpg","noticeList":[{"content":"老铁们，今天放假！"}],"name":"李广","imageList":[{"address":"/upload/image/55887.jpg","name":"产品比较","link":"guge.con"},{"address":"/upload/image/55887.jpg","name":"产品比较","link":"baidu.com"}]}
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
        /**
         * image : /upload/image/touxiang.jpg
         * noticeList : [{"content":"老铁们，今天放假！"}]
         * name : 李广
         * imageList : [{"address":"/upload/image/55887.jpg","name":"产品比较","link":"guge.con"},{"address":"/upload/image/55887.jpg","name":"产品比较","link":"baidu.com"}]
         */

        private String image;
        private String name;
        private List<NoticeListBean> noticeList;
        private List<ImageListBean> imageList;

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

        public List<NoticeListBean> getNoticeList() {
            return noticeList;
        }

        public void setNoticeList(List<NoticeListBean> noticeList) {
            this.noticeList = noticeList;
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public static class NoticeListBean {
            /**
             * content : 老铁们，今天放假！
             */

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ImageListBean {
            /**
             * address : /upload/image/55887.jpg
             * name : 产品比较
             * link : guge.con
             */

            private String address;
            private String name;
            private String link;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
