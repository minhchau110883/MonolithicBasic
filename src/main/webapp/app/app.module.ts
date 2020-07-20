import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MonolithicBasicSharedModule } from 'app/shared/shared.module';
import { MonolithicBasicCoreModule } from 'app/core/core.module';
import { MonolithicBasicAppRoutingModule } from './app-routing.module';
import { MonolithicBasicHomeModule } from './home/home.module';
import { MonolithicBasicEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MonolithicBasicSharedModule,
    MonolithicBasicCoreModule,
    MonolithicBasicHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MonolithicBasicEntityModule,
    MonolithicBasicAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class MonolithicBasicAppModule {}
