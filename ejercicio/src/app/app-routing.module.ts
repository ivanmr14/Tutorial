import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientListComponent } from './client/client-list/client-list.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { PrestamoListComponent } from './prestamo/prestamo-list/prestamo-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/games', pathMatch: 'full'},
  { path: 'clients', component: ClientListComponent },
  { path: 'authors', component: AuthorListComponent },
  { path: 'categories', component: CategoryListComponent },
  { path: 'games', component: GameListComponent },
  { path: 'prestamos', component: PrestamoListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
