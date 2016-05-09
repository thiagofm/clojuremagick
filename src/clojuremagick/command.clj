(ns clojuremagick.command)

(def valid-options
  (sorted-set :adaptive-blur :adaptive-resize :adaptive-sharpen :adjoin :affine :alpha :alpha-color :annotate :antialias :append :attenuate :authenticate :auto-gamma :auto-level :auto-orient :backdrop :background :bench :bias :black-point-compensation :black-threshold :blend :blue-primary :blue-shift :blur :border :bordercolor :borderwidth :brightness-contrast :cache :canny :caption :cdl :channel :charcoal :chop :clamp :clip :clip-mask :clip-path :clone :clut :coalesce :colorize :colormap :color-matrix :colors :colorspace :combine :comment :compare :complex :compose :composite :compress :connected-components :contrast :contrast-stretch :convolve :copy :crop :cycle :debug :decipher :deconstruct :define :delay :delete :density :depth :descend :deskew :despeckle :direction :displace :display :dispose :dissimilarity-threshold :dissolve :distort :distribute-cache :dither :draw :duplicate :edge :emboss :encipher :encoding :endian :enhance :equalize :evaluate :evaluate-sequence :extent :extract :family :features :fft :fill :filter :flatten :flip :floodfill :flop :font :foreground :format :format :frame :frame :function :fuzz :fx :gamma :gaussian-blur :geometry :gravity :grayscale :green-primary :hald-clut :help :highlight-color :hough-lines :iconGeometry :iconic :identify :ift :immutable :implode :insert :intensity :intent :interlace :interpolate :interline-spacing :interword-spacing :kerning :kuwahara :label :lat :layers :level :level-colors :limit :linear-stretch :linewidth :liquid-rescale :list :log :loop :lowlight-color :magnify :map :map :mask :mattecolor :median :mean-shift :metric :mode :modulate :moments :monitor :monochrome :morph :morphology :mosaic :motion-blur :name :negate :noise :normalize :opaque :ordered-dither :orient :page :paint :path :pause :pause :perceptible :ping :pointsize :polaroid :poly :posterize :precision :preview :print :process :profile :quality :quantize :quiet :radial-blur :raise :random-threshold :red-primary :regard-warnings :region :remap :remote :render :repage :resample :resize :respect-parentheses :reverse :roll :rotate :sample :sampling-factor :scale :scene :screen :seed :segment :selective-blur :separate :sepia-tone :set :shade :shadow :shared-memory :sharpen :shave :shear :sigmoidal-contrast :silent :similarity-threshold :size :sketch :smush :snaps :solarize :sparse-color :splice :spread :statistic :stegano :stereo :storage-type :stretch :strip :stroke :strokewidth :style :subimage-search :swap :swirl :synchronize :taint :text-font :texture :threshold :thumbnail :tile :tile-offset :tint :title :transform :transparent :transparent-color :transpose :transverse :treedepth :trim :type :undercolor :unique-colors :units :unsharp :update :verbose :version :view :vignette :virtual-pixel :visual :watermark :wave :wavelet-denoise :weight :white-point :white-threshold :window :window-group :write))

(defn vec->shell-vec
 "Converts vector to conch's shell format"
  [command-vec]
  (vec (apply concat
          (map (fn [command]
                 (let [k (get command 0)
                       v (get command 1)
                       option (str "-" (name k))
                       value v]
                   (when (not (contains? valid-options k))
                     (throw (ex-info "The specified command from Imagemagick is invalid."
                              {:command k})))
                   (remove nil? [option value])))
               command-vec))))
