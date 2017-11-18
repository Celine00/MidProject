# 三国人物志
`2017/11/18`

A mid-term homework project of android development class.
It's a e-dictionary of characters in the period of Three Kingdoms.

## Contributor

[zhoumumu](https://github.com/zhoumumu)
[ZhuYupei](https://github.com/ZhuYupei)
[zoulin]()
[zouyining]()

## Some engagement

1.file: same as the the default format in Android studio  
_eg: activity_something.xml、SomethingActivity.java_ 
2.widget ID: filename\_widgetname\_qualifier
_attention: NO SHORT!_
3.icon: https://material.io/icons/ & png & xxhdpi
4.heros_photos: https://tieba.baidu.com/p/671926886?pn=6

## Update log
write whatever you want your teammates know here...


## Todo
record what function or bug or other things need to be done next here...

1.How about let edit and detail pages share one xml? Settle them down.
2.I'm not sure that in the initSearchButton() in MainActivity.java, will hero_nameTarget get empty string if user never set it? If will, they we have to judge this situation. Also, judge the situation that all conditions are empty.
3.I think search should support fuzzy query
4.Add reset button and logic in drawer_layout
5.BUG---activity_main.xml:gridView.height
     ---MainActivity.java:get wrong heroTarget by searching condition
     ---always get shadow frame"董卓" in drawer
6.other skipping:
    detailpage--back; delete->update main activity
    editpage--new->update main activity & show in detail; cancle->back