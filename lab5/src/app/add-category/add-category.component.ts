import { Component } from '@angular/core';
import { CategoriesService } from '../services/categories.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container">
      <h2>Add New Category</h2>
      <form (ngSubmit)="addCategory()" #categoryForm="ngForm">
        <div>
          <label for="name">Category Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            [(ngModel)]="categoryName"
            required
          />
        </div>
        <div>
          <label for="followers">Followers:</label>
          <input
            type="number"
            id="followers"
            name="followers"
            [(ngModel)]="categoryFollowers"
            required
            min="0"
          />
        </div>
        <button type="submit" [disabled]="!categoryForm.form.valid">
          Add Category
        </button>
        <button type="button" (click)="goBack()">Cancel</button>
      </form>
    </div>
  `,
  styles: [
    `
      .container {
        margin: 20px;
      }
      input {
        margin: 5px 0;
        padding: 8px;
        width: 100%;
      }
      button {
        margin-right: 10px;
        padding: 5px 10px;
      }
    `,
  ],
})
export class AddCategoryComponent {
  categoryName: string = '';
  categoryFollowers: number | null = null;

  constructor(
    private categoriesService: CategoriesService,
    private router: Router
  ) {}

  addCategory() {
    if (this.categoryName.trim() && this.categoryFollowers !== null) {
      const newCategory = {
        name: this.categoryName,
        followers: this.categoryFollowers,
      };

      this.categoriesService.addCategory(newCategory).subscribe(
        () => {
          alert('Category added successfully!');
          this.router.navigate(['/']);
        },
        (error) => {
          console.error('Error adding category', error);
        }
      );
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
