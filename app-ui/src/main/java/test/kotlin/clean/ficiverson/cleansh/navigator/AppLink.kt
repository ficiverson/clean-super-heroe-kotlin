package test.kotlin.clean.ficiverson.cleansh.navigator

import com.airbnb.deeplinkdispatch.DeepLinkSpec
import java.lang.annotation.RetentionPolicy


/**
 * Created by f.souto.gonzalez on 12/09/2018.
 */
@DeepLinkSpec(prefix = arrayOf("heroes://"))
@Retention(AnnotationRetention.SOURCE)
annotation class AppLink(vararg val value: String)