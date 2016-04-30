# clojuremagick

(The cake is still getting baked)

## Why?

Why is it better than using shell command interface of imagemagic directly?

- You just need to specify an vector with the commands, no need to concatenate strings in some funky way.
- You aren't tied to imagemagick, you can use graphicsmagick as well with minimal changes.
- With a single image, you might want to have different versions of it. Clojuremagick handles that for you. (CONCURRENTLY?)
- Just specify the file and a vector with the transformations you need and get the output of that.
- Error handling.

## Examples

```clojure
; Convert a single file -- WARNING: the file will be overriden
(cm/with-file file ; Either file path or clojure.java.io/File / java.io.File
  [[:resize "250x200>"]
   [:rotate "-90"]
   [:flip]])
   
; Convert a file -- keeping the old version. The new version will have the thumb_rotated_small prefix.
(cm/with-copy file
  :thumb-rotated-small
  [[:resize "250x200>"]
   [:rotate "-90"]
   [:flip]])

; Convert a file with multiple outputs (will always keep the old version). The new version will have the prefix specified as the name of the version.
(cm/with-copy
  file
  (cm/version
    :thumb-rotated-small
    [[:resize "100x100"]
     [:rotate "-90"]])
  (cm/version
    :thumb-big
    [[:resize "250x250"]]))
```

## Roadmap

- Basic functionality: load image, call commands over it
- Proper docs
- Compatibility with GraphicsMagick
- Study the possibility of doing convertions concurrently for multiple versions


## License

Copyright © 2016 - Thiago Massa

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
