import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-j2bjmnsnqpq46hpy.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'ZPXzcL4voZEFZ6cis8FaaHMnsd4EYT0Q',
            scope: 'openid profile offline_access email',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            secureRoutes : ['http://localhost:8080/'],
            customParamsAuthRequest:{
              audience: 'http://localhost:8080'
            }
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
