Feature: Gestión de login

  Scenario: Loguear un usuario existente
    Given un usuario existente
    When  se envia una solicitud de login con datos validos
    Then  el usuario se loguea correctamente

  Scenario: Loguear un usuario no registrado
    Given usuario no registrado
    When  se envia una solicitud con datos no registrados
    Then  el usuario no se loguea