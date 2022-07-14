package com.example.statemachine

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.listener.StateMachineListenerAdapter
import org.springframework.statemachine.state.State

@Configuration
@EnableStateMachine
class StateMachineConfig : EnumStateMachineConfigurerAdapter<States, Events>() {

    override fun configure(config: StateMachineConfigurationConfigurer<States, Events>?) {
        config!!.withConfiguration()
            .autoStartup(true)
            .listener(listener())
    }

    override fun configure(states: StateMachineStateConfigurer<States, Events>?) {
        states!!.withStates()
            .initial(States.S0)
            .states(States.values().toSet())
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<States, Events>?) {
        transitions!!
            .withExternal()
            .source(States.S0).target(States.S1).event(Events.E1)
            .and()
            .withExternal()
            .source(States.S1).target(States.S2).event(Events.E2)
    }

    @Bean
    fun listener(): StateMachineListener<States, Events>? {
        return object : StateMachineListenerAdapter<States, Events>() {
            override fun stateChanged(from: State<States, Events>?, to: State<States, Events>?) {
                println("State change to " + to!!.id)
            }
        }
    }
}