package test.kotlin.clean.ficiverson.cleansh.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import test.kotlin.clean.ficiverson.cleansh.injection.GlideApp


fun ImageView.setImageUrl(url: String) {
    setImageUrlWithParams(url, Params())
}

fun ImageView.setImageUrlWithParams(url: String, params: Params) {

    if (url.isNotEmpty()) {

        val gr = GlideApp
            .with(context)
            .load(url)

        gr.error(params.errorResId)
        gr.placeholder(params.defaultResId)

        if (params.transformations.isNotEmpty()) {
            gr.transforms(*params.transformations)
        }

        if (params.fitCenter) gr.fitCenter()

        if (params.centerCrop) gr.centerCrop()

        params.listener?.invoke(false)
        gr.listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                params.listener?.invoke(true)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                params.listener?.invoke(true)
                return false
            }
        })

        gr.into(this)

    } else {
        setImageResource(params.errorResId)
    }
}

class Params {
    var errorResId: Int = 0
    var defaultResId: Int = 0
    var fitCenter: Boolean = false
    var centerCrop: Boolean = false
    var transformations: Array<out Transformation<Bitmap>> = emptyArray()
    var listener: ((Boolean) -> Unit)? = null
    var widthScreenRatio: Float = 1F
    var heightRatio: Float = 1F

    fun errorResId(@DrawableRes errorResId: Int): Params {
        this.errorResId = errorResId
        return this
    }

    fun defaultResId(@DrawableRes defaultResId: Int): Params {
        this.defaultResId = defaultResId
        return this
    }

    fun fitCenter(fit: Boolean): Params {
        this.fitCenter = fit
        return this
    }

    fun centerCrop(centerCrop: Boolean): Params {
        this.centerCrop = centerCrop
        return this
    }

    fun transformations(vararg transformations: Transformation<Bitmap>): Params {
        this.transformations = transformations
        return this
    }

    fun listener(listener: (Boolean) -> Unit): Params {
        this.listener = listener
        return this
    }

    fun imageRatio(widthScreenRatio: Float, heightRatio: Float): Params {
        this.widthScreenRatio = widthScreenRatio
        this.heightRatio = heightRatio
        return this
    }
}