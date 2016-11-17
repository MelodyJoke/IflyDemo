/**
 *
 */
package com.iflytek.ise.result;

import com.iflytek.ise.result.util.ResultFormatUtil;

/**
 * <p>Title: ReadWordResult</p>
 * <p>Description: </p>
 * <p>Company: www.iflytek.com</p>
 *
 * @author iflytek
 */
public class ReadWordResult extends Result {

    public ReadWordResult() {
        category = "read_word";
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        if ("cn".equals(language)) {
            buffer.append("[总体结果]\n").append("评测内容：").append(content).append("\n").append("朗读时长：").append(time_len).append("\n").append("总分：").append(total_score).append("\n\n")
                    .append("[朗读详情]")
                    .append(ResultFormatUtil.formatDetails_CN(sentences));
        } else {
            if (is_rejected) {
                buffer.append("检测到乱读，").append("except_info:").append(except_info).append("\n\n");    // except_info代码说明详见《语音评测参数、结果说明文档》
            }

            buffer.append("[总体结果]\n").append("评测内容：").append(content).append("\n").append("总分：").append(total_score).append("\n\n")
                    .append("[朗读详情]")
                    .append(ResultFormatUtil.formatDetails_EN(sentences));
        }

        return buffer.toString();
    }
}
