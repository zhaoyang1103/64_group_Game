package com.mad.trafficclient.st_java.bean;

import java.util.List;

public class GetAllSuggestion {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"content":"投诉与建议内容","id":11,"phone_number":"13811112222","submit_time":"2019-01-18 01:06:43.0","title":"投诉与建议标题"},{"content":"投诉与建议内容","id":12,"phone_number":"13811112222","submit_time":"2019-01-18 02:22:13.0","title":"投诉与建议标题"},{"id":17,"phone_number":"gfd gfds","submit_time":"2019-03-15 18:44:28.0","title":"fdgfdg fds"},{"id":18,"phone_number":"3123","submit_time":"2019-03-15 19:02:59.0","title":"213"},{"id":19,"phone_number":"3123","submit_time":"2019-03-15 19:03:12.0","title":"213"},{"id":20,"phone_number":"15946jhjkhkjhjuhkjhkjh","submit_time":"2019-03-15 21:50:53.0","title":"ed  "},{"id":21,"phone_number":"sdff\n","submit_time":"2019-03-16 11:29:47.0","title":"23"},{"content":"3213","id":22,"phone_number":"13466954214","submit_time":"2019-03-16 11:31:43.0","title":"21321"},{"content":"意见内容","id":23,"phone_number":"1381111222212","reply_content":"回复内容","reply_time":"2019-03-23 11:16:48.0","submit_time":"2019-03-23 11:16:48.0","title":"意见标题"},{"content":"hellow im nihao ","id":24,"phone_number":"13466995407","reply_content":"回复内容","reply_time":"2019-03-23 06:16:25.0","submit_time":"2019-03-23 06:16:25.0","title":"nihao"},{"content":"意见内容","id":25,"phone_number":"1381111222212","reply_content":"回复内容","reply_time":"2019-03-24 04:25:19.0","submit_time":"2019-03-24 04:25:19.0","title":"意见标题"},{"content":"123","id":26,"phone_number":"13753973040","reply_content":"????","reply_time":"2019-04-28 05:04:39.0","submit_time":"2019-04-28 05:04:39.0","title":"123"}]
     */

    private String ERRMSG;
    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * content : 投诉与建议内容
         * id : 11
         * phone_number : 13811112222
         * submit_time : 2019-01-18 01:06:43.0
         * title : 投诉与建议标题
         * reply_content : 回复内容
         * reply_time : 2019-03-23 11:16:48.0
         */

        private String content;
        private int id;
        private String phone_number;
        private String submit_time;
        private String title;
        private String reply_content;
        private String reply_time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getSubmit_time() {
            return submit_time;
        }

        public void setSubmit_time(String submit_time) {
            this.submit_time = submit_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReply_content() {
            return reply_content;
        }

        public void setReply_content(String reply_content) {
            this.reply_content = reply_content;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }
    }
}
