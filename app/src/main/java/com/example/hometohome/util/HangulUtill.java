package com.example.hometohome.util;

public class HangulUtill {
    public static final int BEGIN_UNICODE = 44032; //가
    public static final int END_UNICODE = 55203; //힣
    public static final int BASE_UNIT = 588; //각 자음마다 가지는 글자수
    public static final char[] INITIAL_SOUND = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    //초성여부 검사
    private static boolean isInitialSound(char searchar){
        for(char c:INITIAL_SOUND){
            if(c == searchar){
                return true;
            }
        }
        return false;
    }

    //자음가져오기
    private static char getInitialSound(char c) {
        int hanBegin = (c - BEGIN_UNICODE);
        int index = hanBegin / BASE_UNIT;
        return INITIAL_SOUND[index];
    }

    //한글인지 검사
    private static boolean isHangul(char c) {
        return BEGIN_UNICODE <= c && c <= END_UNICODE;
    }

    //검색
    // value = 검색대상 search = 검색어
    public static boolean searchString(String value, String search) {
        int t = 0;
        int vslen = value.length() - search.length(); //검색어와 검색대상 길이 비교
        int slen = search.length();
        if (vslen < 0) //검색어가 더 길 경우
            return false;
        for (int i = 0; i <= vslen; i++) {
            t = 0; //검색어 위치 초기화
            while (t < slen) {
                //현재 위치의 단어가 초성 && 한글이면
                if (isInitialSound(search.charAt(t)) == true && isHangul(value.charAt(i + t))) {
                    //초성이 같은지 비교
                    if (getInitialSound(value.charAt(i + t)) == search.charAt(t))
                        t++;
                    else
                        break;
                } else {
                    //초성이 아니면
                    if (value.charAt(i + t) == search.charAt(t))
                        //동일여부만 비교
                        t++;
                    else
                        break;
                }
            }
            if (t == slen)
                return true; //모두 일치하면 true
        }
        return false;
    }
}
