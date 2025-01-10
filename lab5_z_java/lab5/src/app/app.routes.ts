import { Routes } from '@angular/router';
import { CategoriesListComponent } from './categories-list/categories-list.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { EditCategoryComponent } from './edit-category/edit-category.component';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { AddElementComponent } from './add-element/add-element.component';
import { EditElementComponent } from './edit-element/edit-element.component';
import { ElementDetailsComponent } from './element-details/element-details.component';

export const routes: Routes = [
  { path: '', component: CategoriesListComponent },
  { path: 'add-category', component: AddCategoryComponent },
  { path: 'edit-category/:id', component: EditCategoryComponent },
  { path: 'category-details/:id', component: CategoryDetailsComponent },
  { path: 'add-element/:id', component: AddElementComponent },
  {
    path: 'edit-element/:categoryId/:elementId',
    component: EditElementComponent,
  },
  {
    path: 'element-details/:categoryId/:elementId',
    component: ElementDetailsComponent,
  },
];
