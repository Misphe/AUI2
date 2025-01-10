import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriesService } from '../services/categories.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-category',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container">
      <h2>Edit Artist</h2>
      <form (ngSubmit)="updateCategory()">
        <div>
          <label for="name">Artist Name:</label>
          <input
            type="text"
            id="name"
            [(ngModel)]="category.name"
            name="name"
            required
          />
        </div>
        <div>
          <label for="followers">Followers:</label>
          <input
            type="number"
            id="followers"
            [(ngModel)]="category.followers"
            name="followers"
            required
            min="0"
          />
        </div>
        <button type="submit">Update Artist</button>
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
export class EditCategoryComponent implements OnInit {
  category: any = { name: '', followers: 0 };

  constructor(
    private route: ActivatedRoute,
    private categoriesService: CategoriesService,
    private router: Router
  ) {}

  ngOnInit() {
    const categoryId = this.route.snapshot.paramMap.get('id');
    if (categoryId) {
      this.categoriesService.getCategory(categoryId).subscribe(
        (category) => {
          this.category = category;
        },
        (error) => {
          console.error('error fetching artist', error);
        }
      );
    }
  }

  updateCategory() {
    if (this.category.name.trim() && this.category.followers !== null) {
      this.categoriesService.updateCategory(this.category).subscribe(
        () => {
          alert('Artist updated');
          this.router.navigate(['/']);
        },
        (error) => {
          console.error('error updating artist', error);
        }
      );
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
