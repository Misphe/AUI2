import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../services/categories.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './categories-list.component.html',
  styleUrls: ['./categories-list.component.css'],
})
export class CategoriesListComponent implements OnInit {
  categories: any[] = [];
  isLoading = true;

  constructor(
    private categoriesService: CategoriesService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchCategories();
  }

  goToAddCategory() {
    this.router.navigate(['/add-category']);
  }

  goToEditCategory(id: string) {
    this.router.navigate([`/edit-category/${id}`]);
  }

  goToCategoryDetails(id: string) {
    this.router.navigate([`/category-details/${id}`]);
  }

  fetchCategories() {
    this.categoriesService.getCategories().subscribe(
      (data) => {
        this.categories = data;
        this.isLoading = false;
      },
      (error) => {
        console.error('Error fetching categories', error);
        this.isLoading = false;
      }
    );
  }

  deleteCategory(id: string) {
    this.categoriesService.deleteCategory(id).subscribe(
      () => {
        this.categories = this.categories.filter(
          (category) => category.id !== id
        );
      },
      (error) => {
        console.error('Error deleting category', error);
      }
    );
  }
}
