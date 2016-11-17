/**
 *
 */
package com.iflytek.ise.result;

import com.iflytek.ise.result.util.ResultFormatUtil;

/**
 * <p>Title: ReadSyllableResult</p>
 * <p>Description: 中文单字评测结果</p>
 * <p>Company: www.iflytek.com</p>
 *
 * @author iflytek
 */
public class ReadSyllableResult extends Result {

    public ReadSyllableResult() {
        language = "cn";
        category = "read_syllable";
    }

    @Override
    public String toString() {
        return "[总体结果]\n" +
                "评测内容：" + content + "\n" +
                "朗读时长：" + time_len + "\n" +
                "总分：" + total_score + "\n\n" +
                "[朗读详情]" + ResultFormatUtil.formatDetails_CN(sentences);
    }
}
