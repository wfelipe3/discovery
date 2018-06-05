from behave import *
import requests

@given(u'the service with name {name} and url {url} and health check url {healthcheckurl}')
def step_impl(context, name, url, healthcheckurl):
    context.service = {'name': name, 'url': url, 'healthCheckUrl': healthcheckurl}

@when('register is invoked')
def step_impl(context):
    r = requests.post(url="http://localhost:4567/service-discovery/register", json=context.service, headers={"Content-Type": "application/json"})
    print(r.text)
    context.response = r

@then('the service register should return status 200 and the service should be added')
def step_impl(context):
    assert context.response.status_code == 201
        
