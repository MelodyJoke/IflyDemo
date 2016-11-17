/**
 *
 */
package com.iflytek.ise.result.util;

import java.util.ArrayList;

import com.iflytek.ise.result.entity.Phone;
import com.iflytek.ise.result.entity.Sentence;
import com.iflytek.ise.result.entity.Syll;
import com.iflytek.ise.result.entity.Word;

/**
 * <p>Title: ResultFormatUtl</p>
 * <p>Description: </p>
 * <p>Company: www.iflytek.com</p>
 *
 * @author iflytek
 */
public class ResultFormatUtil {

    /**
     * 将英语评测详情按格式输出
     *
     * @return 英语评测详情
     */
    public static String formatDetails_EN(ArrayList<Sentence> sentences) {
        StringBuilder buffer = new StringBuilder();
        if (null == sentences) {
            return buffer.toString();
        }

        for (Sentence sentence : sentences) {
            if ("噪音".equals(ResultTranslateUtil.getContent(sentence.content))
                    || "静音".equals(ResultTranslateUtil.getContent(sentence.content))) {
                continue;
            }

            if (null == sentence.words) {
                continue;
            }
            for (Word word : sentence.words) {
                if ("噪音".equals(ResultTranslateUtil.getContent(word.content))
                        || "静音".equals(ResultTranslateUtil.getContent(word.content))) {
                    continue;
                }

                buffer.append("\n单词[").append(ResultTranslateUtil.getContent(word.content)).append("] ").append("朗读：").append(ResultTranslateUtil.getDpMessageInfo(word.dp_message)).append(" 得分：").append(word.total_score);
                if (null == word.sylls) {
                    buffer.append("\n");
                    continue;
                }

                for (Syll syll : word.sylls) {
                    buffer.append("\n└音节[").append(ResultTranslateUtil.getContent(syll.getStdSymbol())).append("] ");
                    if (null == syll.phones) {
                        continue;
                    }

                    for (Phone phone : syll.phones) {
                        buffer.append("\n\t└音素[").append(ResultTranslateUtil.getContent(phone.getStdSymbol())).append("] ").append(" 朗读：").append(ResultTranslateUtil.getDpMessageInfo(phone.dp_message));
                    }
                }
                buffer.append("\n");
            }
        }

        return buffer.toString();
    }

    /**
     * 将汉语评测详情按格式输出
     *
     * @return 汉语评测详情
     */
    public static String formatDetails_CN(ArrayList<Sentence> sentences) {
        StringBuilder buffer = new StringBuilder();
        if (null == sentences) {
            return buffer.toString();
        }

        for (Sentence sentence : sentences) {
            if (null == sentence.words) {
                continue;
            }

            for (Word word : sentence.words) {
                buffer.append("\n词语[").append(ResultTranslateUtil.getContent(word.content)).append("] ").append(word.symbol).append(" 时长：").append(word.time_len);
                if (null == word.sylls) {
                    continue;
                }

                for (Syll syll : word.sylls) {
                    if ("噪音".equals(ResultTranslateUtil.getContent(syll.content))
                            || "静音".equals(ResultTranslateUtil.getContent(syll.content))) {
                        continue;
                    }

                    buffer.append("\n└音节[").append(ResultTranslateUtil.getContent(syll.content)).append("] ").append(syll.symbol).append(" 时长：").append(syll.time_len);
                    if (null == syll.phones) {
                        continue;
                    }

                    for (Phone phone : syll.phones) {
                        buffer.append("\n\t└音素[").append(ResultTranslateUtil.getContent(phone.content)).append("] ").append("时长：").append(phone.time_len).append(" 朗读：").append(ResultTranslateUtil.getDpMessageInfo(phone.dp_message));
                    }
                }
                buffer.append("\n");
            }
        }

        return buffer.toString();
    }
}
