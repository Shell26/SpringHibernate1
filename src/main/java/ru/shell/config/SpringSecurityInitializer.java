package ru.shell.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
// предписывает Spring активизировать заместитель типа DelegatingFilterProxy, чтобы воспользоваться
// компонентом SpringSecurityFilterChain, прежде чем будет зарегистрирован любой другой фильтр,
// реализующий интерфейс javax.servlet.Filter.
}
