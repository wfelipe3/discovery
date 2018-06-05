Feature: Service discovery
    In order to allow services to find each other
    We implement service discovery

    Scenario Outline:
        Given the service with name <name> and url <url> and health check url <health check url>
        When register is invoked 
        Then the service register should return status 200 and the service should be added
    
    Examples: Services
        | name | url                   | health check url                  |
        | test | http://localhost/test | http://localhost/test/healthcheck |
