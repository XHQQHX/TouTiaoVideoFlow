package com.example.toutiaovideoflow.utils
import com.example.toutiaovideoflow.R

object RandomUtils {
    fun generateRandomNumber(min: Int, max: Int): Int {
        return (min..max).random()
    }
    private val authList = listOf(
        "晚风叙", "云边信笺", "山野来信", "月亮邮递员", "晴空便利店", "风栖梧", "星落枕边", "雾中寻鹿", "屿南", "春山信",
        "海盐汽水", "橘子海畔", "林间鹿鸣", "青柠味风", "浅夏微醺", "温茶叙旧", "晚风告白", "星河入梦", "雾屿", "山野的诗",
        "赴野", "人间过客", "厌世独白", "晚风叙旧", "零度自由", "情绪出逃", "荒野信步", "灵魂摆渡人", "不羁的风", "人间浪客",
        "月亮失约", "暮色掠夺者", "自由失控", "世俗散人", "晚风过境", "厌弃尘埃", "酷到乏味", "孤独患者", "人间倦客", "情绪贩卖机",
        "奶盖小熊", "芝士奶球", "蜜桃气泡", "芋泥啵啵", "草莓兔叽", "奶油泡芙", "软糖邮递员", "小熊打盹", "猫咪揣手", "兔兔迷路",
        "芝士焗饭", "甜柚布丁", "奶糖星球", "樱桃气泡水", "糯米团子", "小熊软糖", "草莓奶冻", "兔子跳跳", "饼干碎了", "软萌兔叽",
        "月下酌酒", "长安旧客", "故里逢春", "南风知意", "折月满兜", "揽星入梦", "江枫渔火", "晚舟唱晚", "青山归远", "云深不知处",
        "月下独酌", "南风未起", "枕上诗书", "星垂平野", "山河入梦", "清风醉酒", "墨染青衣", "一纸江南", "烟雨归期", "长安故里",
        "偷吃月亮", "星星打烊了", "地球观察员", "快乐加载中", "发呆选手", "咸鱼不闲", "奶茶续命", "快乐星球居民", "熬夜冠军", "干饭第一名",
        "月亮打烊", "宇宙漫游指南", "快乐贩卖机", "摸鱼达人", "芝士就是力量", "发呆大赛冠军", "薯片终结者", "奶茶鉴定师", "快乐没烦恼", "宇宙一级吃货"
    )
    fun getRandomAuth(): String {
        return authList.random()
    }
    private val videoUrlList = listOf(
        "android.resource://com.example.toutiaovideoflow/${R.raw.sp1}",
        "android.resource://com.example.toutiaovideoflow/${R.raw.sp2}",
        "http://vjs.zencdn.net/v/oceans.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4",
        "https://www.w3school.com.cn/example/html5/mov_bbb.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209105011F0zPoYzHry.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209104902N3v5Vpxuvb.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/ccff07ce5285890807898977876/v.f42906.mp4",
        "https://vod.pipi.cn/fe5b84bcvodcq1251246104/658e4b085285890797861659749/f0.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/6715a2145285890808041382798/v.f42906.mp4",
        "https://vod.pipi.cn/43903a81vodtransgzp1251246104/bbd4f07a5285890808066187974/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/84ec486e5285890807863862400/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/bb68c7515285890807928280731/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/87d0caf85285890807055577675/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/720db5425285890808000731940/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/67c6e6575285890807968082814/v.f42906.mp4",
        "https://vod.pipi.cn/43903a81vodtransgzp1251246104/2d1fc7685285890807909124510/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/2cb008ef5285890807135914942/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/c3f671d05285890807168094119/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/e1b5eeea5285890806379037311/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/f904d50d5285890806304637095/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/aa5308fc5285890804986750388/v.f42906.mp4",
    )
    fun getRandomVideoUrl(): String {
        return videoUrlList.random()
    }
}