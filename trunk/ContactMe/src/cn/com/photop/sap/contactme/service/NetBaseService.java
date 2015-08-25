package cn.com.photop.sap.contactme.service;



import cn.com.photop.sap.contactme.model.BaseModel;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 网络访问服务基础类
 *
 * @author zhengjh
 *
 */
public abstract class NetBaseService<T extends BaseModel> {

    protected List<T> getResultList(String jsonStr, String jsonArrayName) {
        List<T> list = new ArrayList<T>();
        if (StringUtil.isNotNull(jsonStr)) {
            try {
                JSONObject object = new JSONObject(jsonStr);
                JSONArray jsonArray = object.getJSONArray(jsonArrayName);
                int jsonLength = jsonArray.length();
                for (int i = 0; i < jsonLength; i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                    T t = getBaseModel(jsonobject);
                    list.add(t);
                }

            } catch (JSONException e) {
            }


        }
        return list;
    }

    /**
     * 获取网络访问结果
     *
     * @param jsonStr
     * @return
     */
    protected List<T> getResult(String jsonStr) {
        List<T> data = new ArrayList<T>();
        if (StringUtil.isNotNull(jsonStr)) {
            try {
                JSONObject object = new JSONObject(jsonStr);
                JSONArray jsonArray = object.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.optJSONObject(i);
                    T m = getBaseModel(obj);
                    data.add(m);
                }
            } catch (Exception e) {
            }
        } else {
        }
        return data;
    }

    /**
     * 组装请求参数字符串
     *
     * @param strs
     * @return
     */
    protected String buildRequestStr(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtil.isNotNull(value)) {
                sb.append("&").append(key).append("=").append(value);
            }
        }
        return sb.toString();
    }

    /**
     * 根据jsonBean获取实体类
     *
     * @param bean
     * @return
     */
    protected abstract T getBaseModel(JSONObject obj);
}
