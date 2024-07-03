package com.example.fanponent.dao;

import com.example.fanponent.entity.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    @Override
    default void flush() {

    }

    @Override
    default <S extends Post> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    default <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    default void deleteAllInBatch(Iterable<Post> entities) {

    }

    @Override
    default void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default Post getReferenceById(Long aLong) {
        return null;
    }

    @Override
    default <S extends Post> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    default <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    default <S extends Post> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default List<Post> findAll() {
        return null;
    }

    @Override
    default List<Post> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    default <S extends Post> S save(S entity) {
        return null;
    }

    @Override
    default Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Long aLong) {
        return false;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(Long aLong) {

    }

    @Override
    default void delete(Post entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    default void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default List<Post> findAll(Sort sort) {
        return null;
    }

    @Override
    default Page<Post> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Post> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Post> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends Post> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    default <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
