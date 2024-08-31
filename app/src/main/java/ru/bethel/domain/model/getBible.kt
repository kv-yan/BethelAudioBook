package ru.bethel.domain.model

import ru.bethel.domain.extensions.toSec

private val basicSubTitleList: List<SubTitle> = listOf(
    SubTitle(
        title = "Սկիզբ",
        startPosition = 0.05f,
        startTime = 0.toSec(),
        startEndRange = "00:00 - 01։12",
    ),
    SubTitle(
        title = "Մեջտեղ",
        startPosition = 0.45f,
        startTime = 15.toSec(),
        startEndRange = "01։12 - 02.48"
    ),
    SubTitle(
        title = "Վերջ",
        startPosition = 0.8f,
        startTime = 50.toSec(),
        startEndRange = "02.48 - 05.12"
    ),
)


val oldTestament = listOf(
    BookHead(
        "Ծննդ",
        (1..50).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/1/$it.mp3",
                basicSubTitleList
            )
        },
        1
    ),
    BookHead(
        "Ել",
        (1..40).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/2/$it.mp3",
                basicSubTitleList
            )
        },
        2
    ),
    BookHead(
        "Ղև",
        (1..27).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/3/$it.mp3",
                basicSubTitleList
            )
        },
        3
    ),
    BookHead(
        "Թվ",
        (1..36).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/4/$it.mp3",
                basicSubTitleList
            )
        },
        4
    ),
    BookHead(
        "2Օր",
        (1..34).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/5/$it.mp3",
                basicSubTitleList
            )
        },
        5
    ),
    BookHead(
        "Հեսու",
        (1..24).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/6/$it.mp3",
                basicSubTitleList
            )
        },
        6
    ),
    BookHead(
        "Դատ",
        (1..21).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/7/$it.mp3",
                basicSubTitleList
            )
        },
        7
    ),
    BookHead(
        "Հռութ",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/8/$it.mp3",
                basicSubTitleList
            )
        },
        8
    ),
    BookHead(
        "1 Թագ",
        (1..31).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/9/$it.mp3",
                basicSubTitleList
            )
        },
        9
    ),
    BookHead(
        "2 Թագ",
        (1..25).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/10/$it.mp3",
                basicSubTitleList
            )
        },
        10
    ),
    BookHead(
        "3 Թագ",
        (1..22).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/11/$it.mp3",
                basicSubTitleList
            )
        },
        11
    ),
    BookHead(
        "4 Թագ",
        (1..25).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/12/$it.mp3",
                basicSubTitleList
            )
        },
        12
    ),
    BookHead(
        "1 Մնաց",
        (1..29).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/13/$it.mp3",
                basicSubTitleList
            )
        },
        13
    ),
    BookHead(
        "2 Մնաց",
        (1..36).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/14/$it.mp3",
                basicSubTitleList
            )
        },
        14
    ),
    BookHead(
        "Եզր",
        (1..10).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/15/$it.mp3",
                basicSubTitleList
            )
        },
        15
    ),
    BookHead(
        "Նեեմ",
        (1..13).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/16/$it.mp3",
                basicSubTitleList
            )
        },
        16
    ),
    BookHead(
        "Եսթ",
        (1..10).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/17/$it.mp3",
                basicSubTitleList
            )
        },
        17
    ),
    BookHead(
        "Հոբ",
        (1..42).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/18/$it.mp3",
                basicSubTitleList
            )
        },
        18
    ),
    BookHead(
        "Սաղ",
        (1..150).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/19/$it.mp3",
                basicSubTitleList
            )
        },
        19
    ),
    BookHead(
        "Առ",
        (1..31).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/19/$it.mp3",
                basicSubTitleList
            )
        },
        19
    ),
    BookHead(
        "Ժող",
        (1..12).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/21/$it.mp3",
                basicSubTitleList
            )
        },
        21
    ),
    BookHead(
        "Երգ",
        (1..12).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/22/$it.mp3",
                basicSubTitleList
            )
        },
        22
    ),
    BookHead(
        "Ես",
        (1..66).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/23/$it.mp3",
                basicSubTitleList
            )
        },
        23
    ),
    BookHead(
        "Եր",
        (1..52).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/24/$it.mp3",
                basicSubTitleList
            )
        },
        24
    ),
    BookHead(
        "Ողբ",
        (1..5).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/25/$it.mp3",
                basicSubTitleList
            )
        },
        25
    ),
    BookHead(
        "Եզ",
        (1..48).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/26/$it.mp3",
                basicSubTitleList
            )
        },
        26
    ),
    BookHead(
        "Դան",
        (1..12).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/27/$it.mp3",
                basicSubTitleList
            )
        },
        27
    ),
    BookHead(
        "Ովս",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/28/$it.mp3",
                basicSubTitleList
            )
        },
        28
    ),
    BookHead(
        "Հովել",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/29/$it.mp3",
                basicSubTitleList
            )
        },
        29
    ),
    BookHead(
        "Ամո",
        (1..9).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/30/$it.mp3",
                basicSubTitleList
            )
        },
        30
    ),
    BookHead(
        "Աբդ",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/31/$it.mp3",
                basicSubTitleList
            )
        },
        31
    ),
    BookHead(
        "Հովն",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/32/$it.mp3",
                basicSubTitleList
            )
        },
        32
    ),
    BookHead(
        "Միք",
        (1..7).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/33/$it.mp3",
                basicSubTitleList
            )
        },
        33
    ),
    BookHead(
        "Նաում",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/34/$it.mp3",
                basicSubTitleList
            )
        },
        34
    ),
    BookHead(
        "Ամբ",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/35/$it.mp3",
                basicSubTitleList
            )
        },
        35
    ),
    BookHead(
        "Սոփ",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/36/$it.mp3",
                basicSubTitleList
            )
        },
        36
    ),
    BookHead(
        "Անգ",
        (1..2).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/37/$it.mp3",
                basicSubTitleList
            )
        },
        37
    ),
    BookHead(
        "Զաք",
        (1..14).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/38/$it.mp3",
                basicSubTitleList
            )
        },
        38
    ),
    BookHead(
        "Մաղ",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/39/$it.mp3",
                basicSubTitleList
            )
        },
        39
    )
)

val newTestament = listOf(
    BookHead(
        "Մատ",
        (1..28).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/40/$it.mp3",
                basicSubTitleList
            )
        },
        40
    ),
    BookHead(
        "Մար",
        (1..16).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/41/$it.mp3",
                basicSubTitleList
            )
        },
        41
    ),
    BookHead(
        "Ղուկ",
        (1..24).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/42/$it.mp3",
                basicSubTitleList
            )
        },
        42
    ),
    BookHead(
        "Հովհ",
        (1..21).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/43/$it.mp3",
                basicSubTitleList
            )
        },
        43
    ),
    BookHead(
        "Գործ",
        (1..28).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/44/$it.mp3",
                basicSubTitleList
            )
        },
        44
    ),
    BookHead(
        "Հռ",
        (1..16).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/45/$it.mp3",
                basicSubTitleList
            )
        },
        45
    ),
    BookHead(
        "1 Կոր",
        (1..16).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/46/$it.mp3",
                basicSubTitleList
            )
        },
        46
    ),
    BookHead(
        "2 Կոր",
        (1..13).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/47/$it.mp3",
                basicSubTitleList
            )
        },
        47
    ),
    BookHead(
        "Գաղ",
        (1..6).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/48/$it.mp3",
                basicSubTitleList
            )
        },
        48
    ),
    BookHead(
        "Եփ",
        (1..6).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/49/$it.mp3",
                basicSubTitleList
            )
        },
        49
    ),
    BookHead(
        "Փիլիպ",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/50/$it.mp3",
                basicSubTitleList
            )
        },
        50
    ),
    BookHead(
        "Կող",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/51/$it.mp3",
                basicSubTitleList
            )
        },
        51
    ),
    BookHead(
        "1 Թես",
        (1..5).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/52/$it.mp3",
                basicSubTitleList
            )
        },
        52
    ),
    BookHead(
        "2Թես",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/53/$it.mp3",
                basicSubTitleList
            )
        },
        53
    ),
    BookHead(
        "1 Տիմ",
        (1..6).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/54/$it.mp3",
                basicSubTitleList
            )
        },
        54
    ),
    BookHead(
        "2 Տիմ",
        (1..4).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/55/$it.mp3",
                basicSubTitleList
            )
        },
        55
    ),
    BookHead(
        "Տիտ",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/56/$it.mp3",
                basicSubTitleList
            )
        },
        56
    ),
    BookHead(
        "Փիլիմ",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/57/$it.mp3",
                basicSubTitleList
            )
        },
        57
    ),
    BookHead(
        "Եբր",
        (1..13).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/58/$it.mp3",
                basicSubTitleList
            )
        },
        58
    ),
    BookHead(
        "Հակ",
        (1..5).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/59/$it.mp3",
                basicSubTitleList
            )
        },
        59
    ),
    BookHead(
        "1 Պետ",
        (1..5).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/60/$it.mp3",
                basicSubTitleList
            )
        },
        60
    ),
    BookHead(
        "2 Պետ",
        (1..3).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/61/$it.mp3",
                basicSubTitleList
            )
        },
        61
    ),
    BookHead(
        "1 Հով",
        (1..5).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/62/$it.mp3",
                basicSubTitleList
            )
        },
        62
    ),
    BookHead(
        "2 Հով",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/63/$it.mp3",
                basicSubTitleList
            )
        },
        63
    ),
    BookHead(
        "3 Հով",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/64/$it.mp3",
                basicSubTitleList
            )
        },
        64
    ),
    BookHead(
        "Հուդա",
        (1..1).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/65/$it.mp3",
                basicSubTitleList
            )
        },
        65
    ),
    BookHead(
        "Հայ",
        (1..22).map {
            Chapter(
                "Գլուխ $it",
                "https://www.derekprincearmenia.com/audiofiles/hy/66/$it.mp3",
                basicSubTitleList
            )
        },
        66
    )
)
