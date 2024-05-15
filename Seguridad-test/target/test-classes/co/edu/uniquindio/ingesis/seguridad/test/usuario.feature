
Feature: Gestión de usuarios en una API

  Scenario: Registrar un nuevo usuario
    Given un usuario no registrado
    When  se envia una solicitud de registro con datos validos
    Then  el usuario se registra correctamente
    And   se valida la estructura de la respuesta json

  Scenario: Actualizar un usuario existente
    Given usuario existente
    When  se envia una solicitud con los datos actualizados
    Then  el usuario se actualiza correctamente

  Scenario: Actualizar password de un usuario existente
    Given usuario existente define nuevo password
    When  se envia una solicitud con password actualizado
    Then  el password se actualiza correctamente

  Scenario: Eliminar un usuario existente
    Given usuario existente
    When  se envia una solicitud para eliminar el usuario
    Then  el usuario es eliminado correctamente
