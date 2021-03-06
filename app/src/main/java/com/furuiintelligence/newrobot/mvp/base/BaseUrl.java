package com.furuiintelligence.newrobot.mvp.base;


import com.furuiintelligence.newrobot.R;

/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                   O\  =  /O
 *                ____/`---'\____
 *              .'  \\|     |//  `.
 *             /  \\|||  :  |||//  \
 *            /  _||||| -:- |||||-  \
 *            |   | \\\  -  /// |   |
 *            | \_|  ''\---/''  |   |
 *            \  .-\__  `-`  ___/-. /
 *          ___`. .'  /--.--\  `. . ___
 *       ."" '<  `.___\_<|>_/___.'  >' "".
 *      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *      \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *
 *          佛祖保佑     永不宕机      永无BUG
 *
 *                 大姨夫 2017/4/26
 */
public class BaseUrl {

    public static final int[] IMGS ={
            R.mipmap.home_new,
            R.mipmap.home_customer,
            R.mipmap.home_robot,
            R.mipmap.home_point,
            R.mipmap.home_sales,
            R.mipmap.home_stop

    };

    public static final String[] NAMES ={
            "待办事宜","客户资料","机器人","新品买点","产品销售","紧急停止"
    };

    public static final String DATA = "robot_data";//本地数据库名称
    public static final int RESULTCODE_L = 0;//返回提示码  成功
    public static final int RESULTCODE_ONE = 1;//失败
    public static final int PAGE_SIZI = 1;
    public static final int FINAL_PAGR = 1;
    public static final String TYPE_VALUE_O = "0";
    public static final String TYPE_VALUE_ONE = "1";
    public static final String TYPE_VALUE_TWO = "2";

    //本地数据库 KEY
    public static final String PHONE_NUMBER = "phoneNumber";//销售员手机号
    public static final String NAME = "name";//名称
    public static final String SEX = "sex";//性别
    public static final String AGE = "age";//年龄
    public static final String WORK = "work";//职位
    public static final String NUMBER = "number";//工号
    public static final String SALE_ID = "saleId";//ID
    public static final String IMAGE = "image";//头像
    public static final String IS_ORPERFECT = "IsOrPerfect";
    public static final String CODE = "code";//验证码
    public static final String SUCCESSINDEX = "successIndex";//销售指数
    public static final String SALEINDEX = "saleIndex";//销售能力
    public static final String BRANDID = "brandId";//品牌
    public static final String C_ID = "cid";
    public static final String EXPERIENCE = "experience";//心得体会

    //查询客户资料
    public static final String TYPE = "type";//搜索类型 1日期2 3 4 5
    public static final String KEY_WORD = "keyWord";//请求类型类型
    public static final String C_TYPE = "cType";//今日 1   往期 2
    public static final String ROWS = "rows";//条数
    public static final String PAGE = "page";//页码

    //修改客户资料参数**添加
    public static final String M_ID = "mId";//修改客户资料的 销售员ID
    public static final String M_PHONE = "mPhone";//修改客户手机号
    public static final String M_AGE = "mAge";//年龄
    public static final String M_SEX = "mSex";//性别
    public static final String M_IDCARD = "mIdcard";//身份证
    public static final String M_HOBBY = "mHobby";//爱好
    public static final String M_WORK = "mWork";//工作
    public static final String M_ADDRESS = "mAddress";//地址
    public static final String M_CONTENT = "mContent";//文本
    public static final String M_TRADE = "mTrade";//行业
    public static final String M_INCOME = "mIncome";
    public static final String M_NAME = "mName";//名称


}
