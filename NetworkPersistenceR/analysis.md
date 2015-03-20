Plotting intervals as decreasing points and as persistence diagrams


```r
basepath = "/home/jacobien/git/PersistentHomology/NetworkPersistence/results/"
files = c("condensedMatter", "highEnergyPhysics", "networkScience", "astroPhysics")
require('hexbin')
```

```
## Loading required package: hexbin
```

```r
require('RColorBrewer')
```

```
## Loading required package: RColorBrewer
```

```r
require('ggplot2')
```

```
## Loading required package: ggplot2
```


```r
for(filename in files){
  file = paste(basepath, filename, "0.txt", sep="")
  intervals = read.table(file, F, ",", stringsAsFactors=F)
  dim(intervals)
  weights = sort(union(unique(intervals[,1]), unique(intervals[,2])), decreasing=T)
  
  counts = rep(0, length(weights))
  for(i in 1:dim(intervals)[1]){
    start = which(weights == intervals[i,1])
    end = which(weights == intervals[i,2])
    counts[start:end] = counts[start:end] + 1
  }
  
  plot(weights, counts, pch=20, xlim=c(weights[1],0), main=paste(filename, "0-dim"))


  # 1-dim 
  file = paste(basepath, filename, "1.txt", sep="")
  intervals = read.table(file, F, ",", stringsAsFactors=F)
  dim(intervals)
  weights = sort(union(unique(intervals[,1]), unique(intervals[,2])), decreasing=T)
  min=weights[length(weights)]
  max=weights[1]  
  
  plot(intervals[,1], intervals[,2], xlim=c(min,max), ylim=c(min,max), main=paste(filename, "1-dim"), xlab="", ylab="", pch=20, col="#00000022")
  
#   getcol <- function(i){
#     f = (intervals[i,2]-intervals[i,1]) / (max-min)
#     return(rgb(1-f,1-f,1-f,1))
#   }
#   colors = sapply(1:dim(intervals)[1], function(i){return(getcol(i))})
#   plot(intervals[,1], intervals[,2], xlim=c(min,max), ylim=c(min,max), main=paste(filename, "1-dim"), xlab="", ylab="", pch=20, col=colors)
#   
  if(dim(intervals)[1] > 10){
    hb = hexbin(intervals[,1], intervals[,2])
    plot(hb, xlab="", ylab="", main=paste(filename, "1-dim"))
  }
  
  # 2-dim 
  if(filename != "networkScience"){
    file = paste(basepath, filename, "2.txt", sep="")
    intervals = read.table(file, F, ",", stringsAsFactors=F)
    dim(intervals)
    weights = sort(union(unique(intervals[,1]), unique(intervals[,2])), decreasing=T)
    min=weights[length(weights)]
    max=weights[1]  
    
    plot(intervals[,1], intervals[,2], xlim=c(min,max), ylim=c(min,max), main=paste(filename, "2-dim"), xlab="", ylab="", pch=20, col="#00000022")
    
    if(dim(intervals)[1] > 10){
      hb = hexbin(intervals[,1], intervals[,2])
      plot(hb, xlab="", ylab="", main=paste(filename, "2-dim")) #, colramp=function(n){return(rev(BTC(n+2))[3:(n+2)])})
    }
  }
}
```

![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-1.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-2.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-3.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-4.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-5.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-6.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-7.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-8.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-9.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-10.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-11.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-12.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-13.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-14.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-15.png) ![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-2-16.png) 

#interval.data=data.frame(birth=intervals[,1], death=intervals[,2])
#plot1 <- ggplot(data = interval.data, aes(x = birth, y = death))
#plot1 + stat_binhex()
