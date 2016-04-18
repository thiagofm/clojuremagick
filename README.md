# clojuremagick

(The cake is still getting baked)

## API

```clojure
; Convert a single file -- WARNING: the file will be overriden
(cm/with-file file 
  (cm/resize "250x200>")
  (cm/rotate "-90")
  (cm/flip))
   
; Convert a file -- keeping the old version
(cm/with-copy file
  (cm/resize "250x200>")
  (cm/rotate "-90")
  (cm/flip))

; Convert a file with multiple outputs (will always keep the old version)
(cm/with-copy
  file
  (cm/version
    :thumb-rotated-small
    (cm/resize "100x100")
    (cm/rotate "-90"))
  (cm/version
    :thumb-big
    (cm/resize "250x200")
    (cm/rotate "-90")))
```

## Roadmap

- Basic functionality: load image, call commands over it
- Proper docs
- Compatibility with GraphicsMagick


## License

Copyright © 2016 - Thiago Massa

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
