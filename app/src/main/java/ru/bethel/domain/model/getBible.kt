package ru.bethel.domain.model

import ru.bethel.domain.extensions.toSec

private val basicSubTitleList: List<SubTitle> = listOf(
    SubTitle(title = "Սկիզբ",startPosition = 0.05f,startTime = 0.toSec(),startEndRange = "00:00 - 00։50",),
    SubTitle(title = "Մեջտեղ",startPosition = 0.45f,startTime = 15.toSec(),startEndRange = "00։50 - 02.48"),
    SubTitle(title = "Վերջ",startPosition = 0.8f,startTime = 50.toSec(),startEndRange = "02.48 - 05.12"),
)


val oldTestament = listOf(
    BookHead("Ծննդ","Ծննդոց", (1..50).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/1/$it.mp3", basicSubTitleList, bookIndex = 1 , chapterIndex = it) } ,1 ),
    BookHead("Ել","Ելից", (1..40).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/2/$it.mp3", basicSubTitleList ,bookIndex = 2 , chapterIndex = it) } ,2),
    BookHead("Ղև","Ղևտացոց", (1..27).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/3/$it.mp3", basicSubTitleList , bookIndex = 3 , chapterIndex = it) } ,3),
    BookHead("Թվ","Թվոց", (1..36).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/4/$it.mp3", basicSubTitleList , bookIndex = 4 , chapterIndex = it) } ,4),
    BookHead("2Օր","2 Օրինաց", (1..34).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/5/$it.mp3", basicSubTitleList , bookIndex = 5 , chapterIndex = it) } ,5),
    BookHead("Հեսու","Հեսու", (1..24).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/6/$it.mp3", basicSubTitleList , bookIndex = 6 , chapterIndex = it) } ,6),
    BookHead("Դատ","Դատավորաց", (1..21).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/7/$it.mp3", basicSubTitleList , bookIndex = 7 , chapterIndex = it) } ,7),
    BookHead("Հռութ","Հռութ", (1..4).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/8/$it.mp3", basicSubTitleList , bookIndex = 8 , chapterIndex = it) } ,8),
    BookHead("1 Թագ","1 Թագավորաց", (1..31).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/9/$it.mp3", basicSubTitleList , bookIndex = 9 , chapterIndex = it) } ,9),
    BookHead("2 Թագ","2 Թագավորաց", (1..25).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/10/$it.mp3", basicSubTitleList , bookIndex = 10 , chapterIndex = it) } ,10),
    BookHead("3 Թագ","3 Թագավորաց", (1..22).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/11/$it.mp3", basicSubTitleList , bookIndex = 11 , chapterIndex = it) } ,11),
    BookHead("4 Թագ","4 Թագավորաց", (1..25).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/12/$it.mp3", basicSubTitleList , bookIndex = 12 , chapterIndex = it) } ,12),
    BookHead("1 Մնաց","1 Մնացորդաց", (1..29).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/13/$it.mp3", basicSubTitleList , bookIndex = 13 , chapterIndex = it) } ,13),
    BookHead("2 Մնաց","2 Մնացորդաց", (1..36).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/14/$it.mp3", basicSubTitleList , bookIndex = 14 , chapterIndex = it) } ,14),
    BookHead("Եզր","Եզրաս", (1..10).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/15/$it.mp3", basicSubTitleList , bookIndex = 15 , chapterIndex = it) } ,15),
    BookHead("Նեեմ","Նեեմիա", (1..13).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/16/$it.mp3", basicSubTitleList , bookIndex = 16 , chapterIndex = it) } ,16),
    BookHead("Եսթ","Եսթեր", (1..10).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/17/$it.mp3", basicSubTitleList , bookIndex = 17 , chapterIndex = it) } ,17),
    BookHead("Հոբ","Հոբ", (1..42).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/18/$it.mp3", basicSubTitleList , bookIndex = 18 , chapterIndex = it) } ,18),
    BookHead("Սաղ","Սաղմոս", (1..150).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/19/$it.mp3", basicSubTitleList , bookIndex = 19 , chapterIndex = it) } ,19),
    BookHead("Առ","Առակաց", (1..31).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/19/$it.mp3", basicSubTitleList , bookIndex = 19 , chapterIndex = it) } ,19),
    BookHead("Ժող","Ժողովող", (1..12).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/21/$it.mp3", basicSubTitleList , bookIndex = 21 , chapterIndex = it) } ,21),
    BookHead("Երգ","Երգ Երգոց", (1..12).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/22/$it.mp3", basicSubTitleList , bookIndex = 22 , chapterIndex = it) } ,22),
    BookHead("Ես","Եսայեա", (1..66).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/23/$it.mp3", basicSubTitleList , bookIndex = 23 , chapterIndex = it) } ,23),
    BookHead("Եր","Երեմիա", (1..52).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/24/$it.mp3", basicSubTitleList , bookIndex = 24 , chapterIndex = it) } ,24),
    BookHead("Ողբ","Ողբ Երեմիա", (1..5).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/25/$it.mp3", basicSubTitleList , bookIndex = 25 , chapterIndex = it) } ,25),
    BookHead("Եզ","Եզեկել", (1..48).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/26/$it.mp3", basicSubTitleList , bookIndex = 26 , chapterIndex = it) } ,26),
    BookHead("Դան","Դանիել", (1..12).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/27/$it.mp3", basicSubTitleList , bookIndex = 27 , chapterIndex = it) } ,27),
    BookHead("Ովս","Ովսէ", (1..1).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/28/$it.mp3", basicSubTitleList , bookIndex = 28 , chapterIndex = it) } ,28),
    BookHead("Հովել","Հովել", (1..3).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/29/$it.mp3", basicSubTitleList , bookIndex = 29 , chapterIndex = it) } ,29),
    BookHead("Ամո","Ամովս", (1..9).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/30/$it.mp3", basicSubTitleList , bookIndex = 30 , chapterIndex = it) } ,30),
    BookHead("Աբդ","Աբդդիա", (1..1).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/31/$it.mp3", basicSubTitleList, bookIndex = 31 , chapterIndex = it) } ,31),
    BookHead("Հովն","Հովնան", (1..4).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/32/$it.mp3", basicSubTitleList , bookIndex = 32 , chapterIndex = it) } ,32),
    BookHead("Միք","Միքիա", (1..7).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/33/$it.mp3", basicSubTitleList , bookIndex = 33 , chapterIndex = it) } ,33),
    BookHead("Նաում","Նաում", (1..3).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/34/$it.mp3", basicSubTitleList , bookIndex = 34 , chapterIndex = it) } ,34),
    BookHead("Ամբ","Ամբակւմ", (1..3).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/35/$it.mp3", basicSubTitleList , bookIndex = 35 , chapterIndex = it) } ,35),
    BookHead("Սոփ","Սոփոնիա", (1..3).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/36/$it.mp3", basicSubTitleList , bookIndex = 36 , chapterIndex = it) } ,36),
    BookHead("Անգ","Անգե", (1..2).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/37/$it.mp3", basicSubTitleList , bookIndex = 37 , chapterIndex = it) } ,37),
    BookHead("Զաք","Զաքարիա", (1..14).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/38/$it.mp3", basicSubTitleList , bookIndex = 38 , chapterIndex = it) } ,38),
    BookHead("Մաղ","Մաղաքիա", (1..4).map { Chapter("$it" ,"Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/39/$it.mp3", basicSubTitleList , bookIndex = 39 , chapterIndex = it) } ,39)
)

val newTestament = listOf(
    BookHead("Մատ", "Մատթեոս", (1..28).map { Chapter(shortTitle = "$it", fullTitle = "Գլուխ $it", audioURL = "https://www.derekprincearmenia.com/audiofiles/hy/40/$it.mp3", subTitles =  basicSubTitleList, bookIndex = 40 , chapterIndex = it )  }, 40),
    BookHead("Մար","Մարկոս", (1..16).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/41/$it.mp3", basicSubTitleList ,bookIndex = 41 , chapterIndex = it) } ,41),
    BookHead("Ղուկ","Ղուկաս", (1..24).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/42/$it.mp3", basicSubTitleList ,bookIndex = 42 , chapterIndex = it) } ,42),
    BookHead("Հովհ","Հովհաննես", (1..21).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/43/$it.mp3", basicSubTitleList ,bookIndex = 43 , chapterIndex = it) } ,43),
    BookHead("Գործ","Գործք առաքելոց", (1..28).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/44/$it.mp3", basicSubTitleList , bookIndex = 44 , chapterIndex = it) } ,44),
    BookHead("Հռ","Հռովմաեցիս", (1..16).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/45/$it.mp3", basicSubTitleList , bookIndex = 45 , chapterIndex = it) } ,45),
    BookHead("1 Կոր","1 Կորնթացիս", (1..16).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/46/$it.mp3", basicSubTitleList, bookIndex = 46 , chapterIndex = it) } ,46),
    BookHead("2 Կոր","2 Կորնթացիս", (1..13).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/47/$it.mp3", basicSubTitleList , bookIndex = 47 , chapterIndex = it) } ,47),
    BookHead("Գաղ","Գաղատացիս", (1..6).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/48/$it.mp3", basicSubTitleList , bookIndex = 48 , chapterIndex = it) } ,48),
    BookHead("Եփ","Եփեսացիս", (1..6).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/49/$it.mp3", basicSubTitleList , bookIndex = 49 , chapterIndex = it) } ,49),
    BookHead("Փիլիպ","Փիլիպեցիս", (1..4).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/50/$it.mp3", basicSubTitleList , bookIndex = 50 , chapterIndex = it) } ,50),
    BookHead("Կող","Կողոսացիս", (1..4).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/51/$it.mp3", basicSubTitleList , bookIndex = 51 , chapterIndex = it) } ,51),
    BookHead("1 Թես","1 Թեսացունիկեցիս", (1..5).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/52/$it.mp3", basicSubTitleList , bookIndex = 52 , chapterIndex = it) } ,52),
    BookHead("2Թես","2 Թեսացունիկեցիս", (1..3).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/53/$it.mp3", basicSubTitleList , bookIndex = 53 , chapterIndex = it) } ,53),
    BookHead("1 Տիմ","1 Տիմոթեոս", (1..6).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/54/$it.mp3", basicSubTitleList , bookIndex = 54 , chapterIndex = it) } ,54),
    BookHead("2 Տիմ","2 Տիմոթեոս", (1..4).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/55/$it.mp3", basicSubTitleList , bookIndex = 55 , chapterIndex = it) } ,55),
    BookHead("Տիտ","Տիտոս", (1..3).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/56/$it.mp3", basicSubTitleList , bookIndex = 56 , chapterIndex = it) } ,56),
    BookHead("Փիլիմ","Փիլիմոն", (1..1).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/57/$it.mp3", basicSubTitleList , bookIndex = 57 , chapterIndex = it) } ,57),
    BookHead("Եբր","Եբրայեցիս", (1..13).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/58/$it.mp3", basicSubTitleList , bookIndex = 58 , chapterIndex = it) } ,58),
    BookHead("Հակ","Հակոբոս", (1..5).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/59/$it.mp3", basicSubTitleList , bookIndex = 59 , chapterIndex = it) } ,59),
    BookHead("1 Պետ","1 Պետրոս", (1..5).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/60/$it.mp3", basicSubTitleList , bookIndex = 60 , chapterIndex = it) } ,60),
    BookHead("2 Պետ","2 Պետրոս", (1..3).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/61/$it.mp3", basicSubTitleList , bookIndex = 61 , chapterIndex = it) } ,61),
    BookHead("1 Հով","1 Հովհաննես", (1..5).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/62/$it.mp3", basicSubTitleList , bookIndex = 62 , chapterIndex = it) } ,62),
    BookHead("2 Հով","2 Հովհաննես", (1..1).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/63/$it.mp3", basicSubTitleList , bookIndex = 63 , chapterIndex = it) } ,63),
    BookHead("3 Հով","3 Հովհաննես", (1..1).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/64/$it.mp3", basicSubTitleList , bookIndex = 64 , chapterIndex = it) } ,64),
    BookHead("Հուդա","Հուդա", (1..1).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/65/$it.mp3", basicSubTitleList, bookIndex = 65 , chapterIndex = it) } ,65),
    BookHead("Հայ","Հայտնություն", (1..22).map { Chapter("$it","Գլուխ $it", "https://www.derekprincearmenia.com/audiofiles/hy/66/$it.mp3", basicSubTitleList , bookIndex = 66 , chapterIndex = it) } ,66)
)

val bibleBooks = oldTestament + newTestament
