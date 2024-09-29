package com.codepunk.hollarhype.di.component

import com.codepunk.hollarhype.di.scope.UserScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@UserScope
@DefineComponent(parent = SingletonComponent::class)
interface UserComponent {

    @DefineComponent.Builder
    interface Builder {
        fun build(): UserComponent
    }

}
