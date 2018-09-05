package ag.strider.protector.di

import ag.strider.protector.presentation.LoginActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(app: LoginActivity)

}