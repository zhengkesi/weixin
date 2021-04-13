package com.weixin.publics.utils;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Administrator on 2019/2/26.
 */
public final class SearchTokenizer extends Tokenizer {

    private final TermAttribute termAtt = addAttribute(TermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    private int pos;

    public SearchTokenizer(Reader input){
        super(input);
    }

    @Override
    public final boolean incrementToken() throws IOException {
        clearAttributes();

        while (true) {
            int c = input.read();
            if (c == -1) return false;
            // 只处理数字、字母、汉字
            if (Character.isDigit(c) || Character.isLetter(c) || (c >=19968 && c <= 171941)) {
                termAtt.setTermBuffer(Character.isLetter(c) ? String.valueOf((char) c).toLowerCase() : String.valueOf((char) c));
                termAtt.setTermLength(1);
                offsetAtt.setOffset(correctOffset(pos++), correctOffset(pos));
                return true;
            }

            pos += Character.charCount(c);
        }
    }

    @Override
    public final void end() throws IOException {
        super.end();
        int finalOffset = correctOffset(pos);
        offsetAtt.setOffset(finalOffset, finalOffset);
    }

    @Override
    public final void reset() throws IOException {
        pos = 0;
    }

}
