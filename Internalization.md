# Introduction #

Brief description on how I have implemented internalization in this project, how can end user extend and provide their own language files.

Currently iCAPTCHA comes with two default WordProducers [FactoryLanguageImpl, FactoryRandomImpl] any one can  create new WordProducer by extending 'abstract class IWordFactory' and then calling it using following syntax;
```
MyWordProducerImpl inst=(MyWordProducerImpl)Producer.forName("org.me.MyWordProducerImpl");
```
I have choosen to use abstract class instead of Interface because I wanted to provide so
default implementation for some of the methods so that the person extending/ implementing it wouldn't have to worry about everything.





# Language file definitions. #

Each language pack consist of **two** files

  * .dic
  * .aff

### Example set ###
languagefile.dic languagefile.aff

The **.aff** file contains one line indicating the encoding used itn the **.dic** file,
if **.aff** file is not present default enconding will be assumed _ISO8859-1_
Format for the **.dic** files is simple one word per line
### Example set ###
```
wordA
wordB
wordC
```



# Redistributable Languages #
  * English -ISO8859-1
  * Polish - ISO8859-2
  * Russian - KOI8-R
  * German - ISO8859-1

Language files were downloaded from https://addons.mozilla.org/en-US/firefox/browse/type:3 and are governed by they license agreement, this files have been slightly modified by me.