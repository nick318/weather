import { useQuery, UseQueryOptions } from '@tanstack/react-query';
import { PaginatedResponse, WeatherData } from '../type';

interface PaginationAndSorting {
  pageSize: number;
  pageNum: number;
  sortBy: string;
  sortDir: 'ASC' | 'DESC';
}

export function useWeather(pagination: PaginationAndSorting, queryOptions?: UseQueryOptions<PaginatedResponse<WeatherData>>) {
  return useQuery({
    ...queryOptions,
    queryKey: ['weather', ...Object.values(pagination)],
    queryFn: ({signal}) =>
      fetch(
        `http://localhost:8080/api/weather?pageSize=${pagination.pageSize}&pageNum=${pagination.pageNum}&sortBy=${pagination.sortBy}&sortDir=${pagination.sortDir}`,
        {signal}
      )
        .then(res => res.json())
  });
}