import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { addSongDto } from '../models/addSongDto.model';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  private categoryApiUrl = 'http://java-app:8081/api/aritsts';
  private elementApiUrl = 'http://java-app:8081/api/songs';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(this.categoryApiUrl);
  }

  deleteCategory(id: string): Observable<any> {
    return this.http.delete(`${this.categoryApiUrl}/${id}`, {
      responseType: 'text',
    });
  }

  addCategory(category: any): Observable<any> {
    return this.http.post<any>(this.categoryApiUrl, category);
  }

  getCategory(id: string): Observable<any> {
    return this.http.get<any>(`${this.categoryApiUrl}/${id}`);
  }

  getElement(id: string): Observable<any> {
    return this.http.get<any>(`${this.elementApiUrl}/${id}`);
  }

  updateCategory(category: {
    id: string;
    name: string;
    followers: number;
  }): Observable<any> {
    return this.http.put<any>(
      `${this.categoryApiUrl}/${category.id}`,
      category
    );
  }

  updateElement(element: {
    id: string;
    name: string;
    length: number;
  }): Observable<any> {
    return this.http.put<any>(`${this.elementApiUrl}/${element.id}`, element);
  }

  getElementsByCategoryId(id: string): Observable<any> {
    return this.http.get<any>(`${this.elementApiUrl}/artist/${id}`);
  }

  getCategoryById(id: string): Observable<any> {
    return this.http.get<any>(`${this.categoryApiUrl}/${id}`);
  }

  deleteElement(elementId: string): Observable<void> {
    return this.http.delete<void>(`${this.elementApiUrl}/${elementId}`);
  }

  addElement(categoryId: string, song: addSongDto): Observable<any> {
    return this.http.post<addSongDto>(
      `${this.elementApiUrl}/${categoryId}`,
      song,
      { responseType: 'text' as 'json' }
    );
  }
}
