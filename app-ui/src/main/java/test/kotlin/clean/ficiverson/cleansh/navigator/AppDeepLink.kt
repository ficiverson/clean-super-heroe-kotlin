package test.kotlin.clean.ficiverson.cleansh.navigator

import com.airbnb.deeplinkdispatch.DeepLinkSpec


/**
 * Created by f.souto.gonzalez on 12/09/2018.
 */
@DeepLinkSpec(prefix = arrayOf("heroes://deeplink"))
@Retention(AnnotationRetention.SOURCE)
annotation class AppDeepLink(vararg val value: String)