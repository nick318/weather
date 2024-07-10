export type WeatherData = {
  id: number
  cityId: number
  cityName: string
  main: string
  description: string
  temp: number
  feelsLike: number
  tempMin: number
  tempMax: number
}

export type PaginatedResponse<Data> = {
  totalPages: number;
  totalElements: number;
  first: boolean;
  last: boolean;
  content: Data[]
}